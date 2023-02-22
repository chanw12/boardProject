package board.boardproject.web;


import board.boardproject.domain.dto.LoginFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController
{
    @GetMapping("/login")
    public String login(@ModelAttribute("dto") LoginFormDto dto){

        return "/board/login";

    }
}
