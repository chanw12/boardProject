package board.boardproject.service;

import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostResponseDto;
import board.boardproject.domain.dto.PostRequestDto;
import board.boardproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostRequestDto dto){
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

    @Transactional
    public Long update(Long id ,PostRequestDto dto){
        Post findPost = postRepository.findById(id).orElseThrow(()->
            new IllegalArgumentException("글이 존재하지 않습니다."));
        findPost.update(dto.getTitle(),dto.getWriter(),dto.getContent());
        return id;
    }


}
