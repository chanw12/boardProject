package board.boardproject.web.api;

import board.boardproject.domain.dto.PostSaveDto;
import board.boardproject.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostApiController {
    private final PostService postService;
    @PostMapping("/api/posts")
    public ResponseEntity<PostSaveDto> save(@RequestBody PostSaveDto dto){
        Long saveId = postService.save(dto);
        log.info(dto.toEntity().getContent());

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok().headers(headers).body(dto);
    }
}
