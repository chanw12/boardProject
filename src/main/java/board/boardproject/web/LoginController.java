package board.boardproject.web;


import board.boardproject.domain.dto.LoginFormDto;
import org.apache.catalina.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController
{
    @GetMapping("/login")
    public String login(@RequestParam(value = "error",required = false)String error,
                        @RequestParam(value = "exception",required = false)String exception,
                        Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:board/list";
        }
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "/board/login";

    }
//    @PostMapping("/logout")
//    public String logout(){
//        return "redirect:/login";
//    }


}
