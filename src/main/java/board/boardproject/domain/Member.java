package board.boardproject.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String nickname;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;


    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}

