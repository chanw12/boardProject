package board.boardproject.service;

import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostRequestDto;

import board.boardproject.domain.dto.PostResponseDto;
import board.boardproject.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Profile(value = "test")
@Rollback(value = false)
class PostServiceTest {

    @AfterEach
    void clear(){
        postRepository.deleteAll();
    }

    @Autowired
    PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test
    void save() {
    }

    @Test
    void findAll_By_createTime() {
    }

    @Test
    void update() {

        PostRequestDto dto = PostRequestDto.builder().title("chan").writer("kang").content("ggg").build();

        Long saveId = postService.save(dto);
        postService.update(saveId,new PostRequestDto("바뀜","나도바꿈","content zzz"));
        Assertions.assertThat(postService.findOne(saveId).getTitle()).isEqualTo("바뀜");


    }

    @Test
    void delete(){
        //given
        PostRequestDto dto1 = PostRequestDto.builder().title("chan").writer("kang").content("ggg").build();
        Long saveId1 = postService.save(dto1);

        PostRequestDto dto2 = PostRequestDto.builder().title("chan").writer("kanga").content("gggaa").build();
        Long saveId2 = postService.save(dto2);
        //when

        postService.deleteOne(saveId1);

        //then

        Assertions.assertThat(postRepository.findAll().size()).isEqualTo(1);
    }
}