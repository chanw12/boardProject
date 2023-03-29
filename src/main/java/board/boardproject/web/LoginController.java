package board.boardproject.web;
import board.boardproject.domain.dto.CheckDto;
import board.boardproject.domain.dto.LoginFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class LoginController
{
    private final SessionRegistry sessionRegistry;

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
    @GetMapping("/logincheck")
    public String logincheck(Model model, LoginFormDto dto){
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        boolean alert = false;

        for (Object principal :allPrincipals){
            User user = (User) principal;
            if (user.getUsername().equals(dto.getUsername())){
                alert= true;
            }

        }

        model.addAttribute("dto",dto);
        model.addAttribute("loginalert",alert);
        return "/board/logincheck";
    }




}
