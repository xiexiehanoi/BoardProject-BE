package com.yellowstone.boardback.service.implement;

import com.yellowstone.boardback.dto.response.ResponseDto;
import com.yellowstone.boardback.dto.response.board.GetPopularListResponseDto;
import com.yellowstone.boardback.dto.response.board.GetRelationListResponseDto;
import com.yellowstone.boardback.repository.SearchLogRepository;
import com.yellowstone.boardback.repository.resultSet.GetPopularListResultSet;
import com.yellowstone.boardback.repository.resultSet.GetRelationListResultSet;
import com.yellowstone.boardback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList(){
        List<GetPopularListResultSet> resultSets = new ArrayList<>();
        try {

            resultSets = searchLogRepository.getPopularList();

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

        List<GetRelationListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getRelationList(searchWord);

        } catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetRelationListResponseDto.success(resultSets);
        }
}
