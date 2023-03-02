package board.boardproject.domain.dto;

import board.boardproject.domain.Post;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String writer;

    @NotBlank
    private String content;

    public Post toEntity(){
        Post post = new Post(title,content,writer);
        return post;
    }
}
