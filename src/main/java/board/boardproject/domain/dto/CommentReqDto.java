package board.boardproject.domain.dto;

import board.boardproject.domain.Comment;
import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentReqDto {

    @NotBlank
    private String content;

    private Member member;
    private Post post;



    public Comment toEntity(){
        Comment comment = new Comment(content,member,post);
        return comment;
    }
}
