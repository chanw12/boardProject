package board.boardproject.service;

import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostResponseDto;
import board.boardproject.domain.dto.PostRequestDto;
import board.boardproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    public List<PostResponseDto> findAll_By_createTime(Pageable pageable){
        return postRepository.findAll(pageable)
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

    @Transactional
    public PostResponseDto findOne(Long id){
        return new PostResponseDto(postRepository.findById(id).get());
    }
    @Transactional
    public void deleteOne(Long id){
        Post findPost = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("글이 존재하지 않습니다"));
        postRepository.delete(findPost);
    }

    /**
     * paging
     */
    @Transactional
    public Page<Post> getPage(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @Transactional
    public List<Integer> getPageList(Pageable pageable) {
        int currentPage = pageable.getPageNumber()+1; // 0-based 인덱스를 1-based 인덱스로 변환
        int totalPage = postRepository.findAll(pageable).getTotalPages(); // 총 페이지 수

        int startPage = Math.max(1, currentPage - 5);
        int endPage = Math.min(totalPage, currentPage + 5);

        List<Integer> pageList = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageList.add(i);
        }
        return pageList;
    }

    @Transactional
    public List<PostResponseDto> searchTitletFind(String keyword,Pageable pageable){
        return postRepository.findPostByTitleContaining(keyword,pageable)
                .stream().map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Integer> getSearchPageList(String keyword,Pageable pageable) {
        int currentPage = pageable.getPageNumber()+1; // 0-based 인덱스를 1-based 인덱스로 변환
        int totalPage = postRepository.findPostByTitleContaining(keyword,pageable).getTotalPages(); // 총 페이지 수

        int startPage = Math.max(1, currentPage - 5);
        int endPage = Math.min(totalPage, currentPage + 5);

        List<Integer> pageList = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageList.add(i);
        }
        return pageList;
    }









}
