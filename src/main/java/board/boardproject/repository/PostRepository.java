package board.boardproject.repository;

import board.boardproject.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select p from Post p order by p.createDate desc")
    List<Post> findAll_By_createTime();
}


