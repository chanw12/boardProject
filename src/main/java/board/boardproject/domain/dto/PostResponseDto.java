package board.boardproject.domain.dto;

import board.boardproject.domain.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdDate, modifiedDate;
    private int view;

    /* Entity -> Dto*/
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getMember().getNickname();
        this.content = post.getContent();
        this.createdDate = post.getCreateDate();
        this.modifiedDate = post.getModifiedDate();
    }
}
