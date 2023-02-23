package board.boardproject.domain.dto;

import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberResponseDto {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createdDate, modifiedDate;
    private String nickname;


    public MemberResponseDto(Member member){
        this.id = member.getId();
        this.createdDate = member.getCreateDate();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.modifiedDate = member.getModifiedDate();

    }
}
