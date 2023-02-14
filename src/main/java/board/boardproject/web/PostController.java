package board.boardproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/board/list")
    public String hello(Model model){

        return "board/list";
    }
}
