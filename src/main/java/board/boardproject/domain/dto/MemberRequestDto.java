package board.boardproject.domain.dto;

import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {



    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String nickname;


    public Member toEntity(){
        Member member = new Member(username,password,nickname);
        return member;
    }
}
