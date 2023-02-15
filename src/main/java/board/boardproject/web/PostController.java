package board.boardproject.web;

import board.boardproject.domain.dto.PostRequestDto;
import board.boardproject.repository.PostRepository;
import board.boardproject.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final PostRepository postRepository;

    @GetMapping("/board/list")
    public String hello(Model model){

        model.addAttribute("boardList",postService.findAll_By_createTime());

        return "board/list";
    }
    @GetMapping("/board/post")
    public String write(Model model, PostRequestDto dto){
        model.addAttribute("dto",dto);
        return "/board/write";
    }


    /**
     *
     * 밑 부분은 restcontroller를 사용하려던 요청에 대한 api들을 모아놓았다.
     */

    @PostMapping("/api/posts")
    public String save(PostRequestDto dto){
        Long saveId = postService.save(dto);
        return "redirect:/board/list";
    }



}
