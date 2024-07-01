package com.yellowstone.boardback.domain.board.dto.object;

import com.yellowstone.boardback.domain.board.repository.resultset.GetCommentListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItem {
    private String nickname;
    private String profileImage;
    private String writeDatetime;
    private String comment;

    public CommentListItem(GetCommentListResultSet resultSet){
        this.nickname = resultSet.getNickname();
        this.profileImage = resultSet.getProfileImage();
        this.writeDatetime = resultSet.getWriteDatetime();
        this.comment = resultSet.getComment();
    }

    public static List<CommentListItem> copyList(List<GetCommentListResultSet> resultSets){
        List<CommentListItem> list = new ArrayList<>();
        for(GetCommentListResultSet resultSet : resultSets){
            CommentListItem commentListItem = new CommentListItem(resultSet);
            list.add(commentListItem);
        }
        return list;
    }
}
