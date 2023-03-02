package board.boardproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    @Size(min = 2,max = 20,message = "제목은 2자에서 20자 이내로 이루어져 있어야합니다")
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    @Column(nullable = false)
    @Size(min=2,max=10)
    private String writer;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Post(String title, String content, String writer,Member member) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.member = member;
    }

    public void update(String title,String writer, String content){
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
