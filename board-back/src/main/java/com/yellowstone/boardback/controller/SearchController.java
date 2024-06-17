package com.yellowstone.boardback.controller;

import com.yellowstone.boardback.dto.response.board.GetPopularListResponseDto;
import com.yellowstone.boardback.dto.response.board.GetRelationListResponseDto;
import com.yellowstone.boardback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularlist() {
        ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
        return response;
    }

    @GetMapping("/{searchWord}/relation-list")
    public ResponseEntity <? super GetRelationListResponseDto> getRelationList(
            @PathVariable("searchWord") String searchWord
    ){
    ResponseEntity <? super GetRelationListResponseDto> response = searchService.getRelationList(searchWord);
    return response;
    }

}
