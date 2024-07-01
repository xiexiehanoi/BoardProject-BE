package com.yellowstone.boardback.domain.search.service.implement;

import com.yellowstone.boardback.common.ResponseDto;
import com.yellowstone.boardback.domain.search.dto.response.GetPopularListResponseDto;
import com.yellowstone.boardback.domain.search.dto.response.GetRelationListResponseDto;
import com.yellowstone.boardback.domain.search.repository.SearchLogRepository;
import com.yellowstone.boardback.domain.search.repository.resultset.GetPopularListResultSet;
import com.yellowstone.boardback.domain.search.repository.resultset.GetRelationListResultSet;
import com.yellowstone.boardback.domain.search.service.SearchService;
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
