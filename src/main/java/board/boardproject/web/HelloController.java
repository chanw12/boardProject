package board.boardproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/detail")
    public String detail(Model model){
        return "/board/detail";
    }
    @GetMapping("/update")
    public String update(Model model){
        return "/board/update";
    }
    @GetMapping("/write")
    public String write(Model model){
        return "/board/write";
    }
}
