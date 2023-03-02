package board.boardproject.web;


import board.boardproject.domain.dto.MemberRequestDto;
import board.boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController{
    private final MemberService memberService;
    @GetMapping("/register")
    public String register(@ModelAttribute("dto")MemberRequestDto dto){

        return "/board/register";
    }

    @PostMapping("/register")
    public String registerMember(@Valid MemberRequestDto dto, Errors errors, Model model){
        if (errors.hasErrors()){
            Map<String, String> validateResult = memberService.validateHandling(errors);
            for (String key : validateResult.keySet()){
                model.addAttribute(key,validateResult.get(key));
                System.out.println(key);
            }
            model.addAttribute("dto",dto);
            return "/board/register";
        }
        memberService.save(dto);
        return "redirect:/login";
    }

}
