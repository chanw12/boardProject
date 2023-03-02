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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Map<String,String> validateHandling(Errors errors){
        Map<String,String> validatorResult = new HashMap<>();

        for (FieldError error: errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }


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

    /**
     * search paging
     */
    @Transactional
    public List<PostResponseDto> searchFind(String type,String keyword,Pageable pageable){
        Page<Post> searchResult;
        switch (type) {
            case "title":
                searchResult = postRepository.findPostByTitleContaining(keyword, pageable);
                break;
            case "content":
                searchResult = postRepository.findPostByContentContaining(keyword, pageable);
                break;
            case "writer":
                searchResult = postRepository.findPostByWriterContaining(keyword, pageable);
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 타입입니다: " + type);
        }
        List<PostResponseDto> postResponseDtos = searchResult.getContent()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
        return postResponseDtos;
    }


    @Transactional
    public List<Integer> getSearchPageList(String type,String keyword,Pageable pageable) {
        Page<Post> searchResult;
        switch (type) {
            case "title":
                searchResult = postRepository.findPostByTitleContaining(keyword, pageable);
                break;
            case "content":
                searchResult = postRepository.findPostByContentContaining(keyword, pageable);
                break;
            case "writer":
                searchResult = postRepository.findPostByWriterContaining(keyword, pageable);
                break;
            default:
                throw new IllegalArgumentException("타입이 정의 되지 않았습니다");
        }
        int currentPage = searchResult.getNumber() + 1;
        int startPage = Math.max(1, currentPage - 5);
        int endPage = Math.min(searchResult.getTotalPages(), currentPage + 5);

        List<Integer> pageList = new ArrayList<>();
        for (int i = startPage; i <= endPage; i++) {
            pageList.add(i);
        }
        return pageList;
    }










}
