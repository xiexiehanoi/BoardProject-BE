package com.yellowstone.boardback.domain.board.service.implement;

import com.yellowstone.boardback.domain.board.dto.request.PatchBoardRequestDto;
import com.yellowstone.boardback.domain.board.dto.request.PostBoardRequestDto;
import com.yellowstone.boardback.domain.board.dto.request.PostCommentRequestDto;
import com.yellowstone.boardback.board.dto.response.*;
import com.yellowstone.boardback.board.entity.*;
import com.yellowstone.boardback.board.repository.*;
import com.yellowstone.boardback.common.ResponseDto;
import com.yellowstone.boardback.domain.board.dto.response.*;
import com.yellowstone.boardback.domain.board.entity.*;
import com.yellowstone.boardback.domain.board.repository.*;
import com.yellowstone.boardback.domain.board.repository.resultset.GetBoardResultSet;
import com.yellowstone.boardback.domain.board.repository.resultset.GetCommentListResultSet;
import com.yellowstone.boardback.domain.board.repository.resultset.GetFavoriteListResultSet;
import com.yellowstone.boardback.domain.search.entity.SearchLogEntity;
import com.yellowstone.boardback.domain.search.repository.SearchLogRepository;
import com.yellowstone.boardback.domain.board.service.BoardService;
import com.yellowstone.boardback.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    private final BoardListViewRepository boardListViewRepository;
    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {

        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try {

            resultSet = boardRepository.getBoard(boardNumber);
            if(resultSet == null) return GetBoardResponseDto.notExistBoard();

            imageEntities = imageRepository.findByBoardNumber(boardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetBoardResponseDto.success(resultSet, imageEntities);
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {

        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

        try{

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedBoard) return GetFavoriteListResponseDto.noExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardNumber);

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetFavoriteListResponseDto.succeess(resultSets);
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

        List<GetCommentListResultSet> resultSets = new ArrayList<>();

        try{

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedBoard) return GetCommentListResponseDto.noExistBoard();

            resultSets = commentRepository.getCommentList(boardNumber);

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {

            boardListViewEntities = boardListViewRepository.findByOrderByWriteDateTimeDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
            return GetLatestBoardListResponseDto.succeess(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {
            Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sevenDaysAgo = simpleDateFormat.format(beforeWeek);
            boardListViewEntities = boardListViewRepository.findTop3ByWriteDateTimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDateTimeDesc(sevenDaysAgo);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
            return GetTop3BoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord) {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try{

            boardListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrderByWriteDateTimeDesc(searchWord, searchWord);


            SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord,preSearchWord,false);
            searchLogRepository.save(searchLogEntity);
            boolean relation = preSearchWord !=null;
            if(relation){
                searchLogEntity = new SearchLogEntity(preSearchWord, searchWord, relation);
                searchLogRepository.save(searchLogEntity);
            }



        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {
        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return GetUserBoardListResponseDto.noExistUser();

            boardListViewEntities = boardListViewRepository.findByWriterEmailOrderByWriteDateTimeDesc(email);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }

        return GetUserBoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {

        try{

            boolean existedEmail = userRepository.existsByEmail(email);
            if(!existedEmail) return PostBoardResponseDto.notExistUser();


            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image: boardImageList){
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email) {

        try{

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null ) return PostCommentResponseDto.noExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PostCommentResponseDto.noExistUser();

            CommentEntity commentEntity = new CommentEntity(dto, boardNumber, email);
            commentRepository.save(commentEntity);

            boardEntity.increaseComment();
            boardRepository.save(boardEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {


        try{

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PutFavoriteResponseDto.noExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return PutFavoriteResponseDto.noExistBoard();

            boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
            if(!existedBoard) return PutFavoriteResponseDto.noExistBoard();

            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardNumber, email);
            if (favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(email, boardNumber);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            }
            else{
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email) {
        try {

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return PatchBoardResponseDto.noExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if (!existedUser) return PatchBoardResponseDto.noExistUser();

            String writerEmail = boardEntity.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if (!isWriter) return PatchBoardResponseDto.noPermission();

            boardEntity.patchBoard(dto);
            boardRepository.save(boardEntity);

            imageRepository.deleteByBoardNumber(boardNumber);
            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for(String image: boardImageList){
                ImageEntity imageEntity = new ImageEntity(boardNumber,image);
                imageEntities.add(imageEntity);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchBoardResponseDto.success();
    }
    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {
        try{
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return IncreaseViewCountResponseDto.notExistBoard();

            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IncreaseViewCountResponseDto.succeess();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {

        try{
            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return DeleteBoardResponseDto.noExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if(boardEntity == null) return DeleteBoardResponseDto.noExistBoard();

            String writerEmail = boardEntity.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return DeleteBoardResponseDto.noPermission();

            //게시물 관련 내용 삭제
            imageRepository.deleteByBoardNumber(boardNumber);
            commentRepository.deleteByBoardNumber(boardNumber);
            favoriteRepository.deleteByBoardNumber(boardNumber);

            //게시물 삭제
            boardRepository.delete(boardEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardResponseDto.success();

    }
}
