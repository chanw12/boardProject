package board.boardproject.web;

import board.boardproject.repository.PostRepository;
import board.boardproject.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final PostRepository postRepository;

    @GetMapping("/board/list")
    public String hello(Model model){
<<<<<<< HEAD
        model.addAttribute("boardList",postService.findAll_By_createTime());
=======
>>>>>>> make_page
        return "board/list";
    }
}
