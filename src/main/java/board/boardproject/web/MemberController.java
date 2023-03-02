package board.boardproject.web;


import board.boardproject.domain.dto.MemberRequestDto;
import board.boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/register")
    public String register(@ModelAttribute("dto")MemberRequestDto dto){

        return "/board/register";
    }

    @PostMapping("/register")
    public String registerMember(MemberRequestDto dto){
        memberService.save(dto);
        return "redirect:/login";
    }

}
