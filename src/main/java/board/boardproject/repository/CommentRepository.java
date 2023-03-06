package board.boardproject.repository;

import board.boardproject.domain.Comment;
import board.boardproject.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Page<Comment> findAllByPostId(Long id,Pageable page);
    Page<Comment> findAllByPost(Post post,Pageable page);
}
