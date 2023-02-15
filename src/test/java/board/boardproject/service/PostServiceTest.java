package board.boardproject.service;

import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostRequestDto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void save() {
    }

    @Test
    void findAll_By_createTime() {
    }

    @Test
    void update() {

        PostRequestDto dto = PostRequestDto.builder().title("chan").writer("kang").content("ggg").build();
        Post post = dto.toEntity();

        post.update("kkk","초","ㅋㅋㅋ");
        Assertions.assertThat(post.getTitle()).isEqualTo("kkk");



    }
}