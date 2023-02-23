package board.boardproject.web;


import board.boardproject.domain.dto.MemberRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MemberController {

    @GetMapping("/register")
    public String register(@ModelAttribute("dto")MemberRequestDto dto){

        return "/board/register";
    }
}
