package board.boardproject.repository;

import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findPostByTitleContaining(String keyword, Pageable pageable);

}


