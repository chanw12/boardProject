package board.boardproject.domain.dto;

import board.boardproject.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDto {

    private String title;
    private String writer;
    private String content;

    public Post toEntity(){
        Post post = new Post(title,content,writer);
        return post;
    }
}
