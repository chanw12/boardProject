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
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Long Id;

    @Column(nullable = false ,length = 255)
    private String content;


    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void update(String content){
        this.content = content;
    }

    public Comment(String content,Member member , Post post){
        this.content = content;
        this.member = member;
        this.post = post;
    }

}
