package com.yellowstone.boardback.entity;

import com.yellowstone.boardback.dto.request.board.PatchBoardRequestDto;
import com.yellowstone.boardback.dto.request.board.PostBoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber;
    private String title;
    private String content;
    private String writeDatetime;
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writerEmail;

    public BoardEntity(PostBoardRequestDto dto, String email){

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.writeDatetime = writeDatetime;
        this.viewCount = 0;
        this.commentCount = 0;
        this.favoriteCount = 0;
        this.writerEmail = email;
    }

    public void increaseViewCount() {
        this.viewCount++;
    }
    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }
    public void increaseComment() {this.commentCount++;}
    public void decreaseFavoriteCount() {
        this.favoriteCount--;
    }
    public void patchBoard(PatchBoardRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }


}