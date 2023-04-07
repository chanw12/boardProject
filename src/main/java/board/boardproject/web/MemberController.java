package board.boardproject.web;


import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import board.boardproject.domain.dto.MemberRequestDto;
import board.boardproject.repository.PostRepository;
import board.boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController{
    private final MemberService memberService;
    private final PostRepository postRepository;

    @GetMapping("/profile")
    public String profile(@RequestParam("nickname") String nickname, Model model,@PageableDefault(sort = "createDate",direction = Sort.Direction.DESC,size = 5) Pageable pageable){
        Member findMember = memberService.findOneByNickname(nickname).get();
        Page<Post> postPage = postRepository.findAllByMemberNickname(nickname,pageable);
        List<Post> postList = postPage.getContent();
        model.addAttribute("member",findMember);
        model.addAttribute("posts",postList);
        model.addAttribute("pageable", postPage);
        model.addAttribute("currentPage", postPage.getNumber() + 1);
        model.addAttribute("totalPages", postPage.getTotalPages());
        return "board/profile";

    }



    @GetMapping("/register")
    public String register(@ModelAttribute("dto")MemberRequestDto dto){

        return "board/register";
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
            return "board/register";
        }
        else{
            try{
                memberService.save(dto);
            }catch(IllegalStateException e){
                model.addAttribute("errors",e.getMessage());
                model.addAttribute("dto",dto);
                return "board/register";
            }
            return "redirect:/login";
        }


    }

}
