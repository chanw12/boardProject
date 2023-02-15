package board.boardproject.service;

import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostResponseDto;
import board.boardproject.domain.dto.PostSaveDto;
import board.boardproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveDto dto){
        Post post = dto.toEntity();
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public List<PostResponseDto> findAll_By_createTime(){
        return postRepository.findAll_By_createTime()
                .stream().map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}
