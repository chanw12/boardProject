package board.boardproject.web;


import board.boardproject.domain.dto.LoginFormDto;
import org.apache.catalina.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController
{
    @GetMapping("/login")
    public String login(@ModelAttribute("dto") LoginFormDto dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {

            return "redirect:board/list";
        }
        return "/board/login";

    }
    @PostMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }


}
