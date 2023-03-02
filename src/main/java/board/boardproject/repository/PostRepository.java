package board.boardproject.repository;

import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findPostByTitleContaining(String keyword, Pageable pageable);
    Page<Post> findPostByContentContaining(String keyword,Pageable pageable);
    Page<Post> findPostByWriterContaining(String keyword,Pageable pageable);
    Page<Post> findAllByWriter(String nickname, Pageable pageable);
}


