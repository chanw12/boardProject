package board.boardproject.web.api;

import board.boardproject.domain.dto.PostRequestDto;
import board.boardproject.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostApiController {
    private final PostService postService;
//    @PostMapping("/api/posts")
//    public ResponseEntity<PostRequestDto> save(@RequestBody PostRequestDto dto){
//        Long saveId = postService.save(dto);
//        log.info(dto.toEntity().getContent());
//
//        return ResponseEntity.ok().body(dto);
//    }

//    @PostMapping("/api/posts/{id}")
//    public ResponseEntity<Long> update(@PathVariable("id") Long id,@RequestBody PostRequestDto dto){
//        if (id == null){
//            return ResponseEntity.notFound().build();
//        }else{
//            return ResponseEntity.ok(postService.update(id,dto));
//        }
//
//    }

}
