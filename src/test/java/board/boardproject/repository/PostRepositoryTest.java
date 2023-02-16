package board.boardproject.repository;

import board.boardproject.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void clear(){
        postRepository.deleteAll();
    }

    @Test
    public void createAndSavePost(){
        Post post = new Post("안녕하새요 가입했어요","안녕하세요 강찬우 라고 합니다 ","chanw12");
        postRepository.save(post);
        Post findPost = postRepository.findById(post.getId()).get();
        assertThat(post.getId()).isEqualTo(findPost.getId());

    }

}