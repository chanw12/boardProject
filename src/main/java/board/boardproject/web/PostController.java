package board.boardproject.web;

import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import board.boardproject.domain.dto.PostRequestDto;
import board.boardproject.domain.dto.PostResponseDto;
import board.boardproject.repository.PostRepository;
import board.boardproject.service.MemberService;
import board.boardproject.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final PostRepository postRepository;

    @GetMapping("/board/list")
    public String hello(@PageableDefault(sort = "createDate",direction = Sort.Direction.DESC) Pageable pageable, Model model, @AuthenticationPrincipal UserDetails user){
        model.addAttribute("boardList",postService.findAll_By_createTime(pageable));
        model.addAttribute("pageList",postService.getPageList(pageable));
        Member member = memberService.findOneByUsername(user.getUsername()).get();
        model.addAttribute("nickname",member.getNickname());

        return "board/list";
    }
    @GetMapping("/board/post")
    public String write(Model model, PostRequestDto dto, @AuthenticationPrincipal UserDetails user){

        Member member = memberService.findOneByUsername(user.getUsername()).get();
        model.addAttribute("nickname",member.getNickname());
        dto.setWriter(member.getNickname());
        model.addAttribute("dto",dto);

        return "/board/write";
    }

    @GetMapping("/board/post/{id}")
    public String detail(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetails user){
        if(setCommonAttributes(model,id,user)){
            model.addAttribute("isOwn",true);
        }else{
            model.addAttribute("isOwn",false);
        }
        return "/board/detail";
    }
    @GetMapping("/board/post/edit/{id}")
    public String update(Model model,@PathVariable Long id,@AuthenticationPrincipal UserDetails user){
        PostResponseDto dto = postService.findOne(id);
        model.addAttribute("boardDto",dto);
        Member member = memberService.findOneByUsername(user.getUsername()).get();
        model.addAttribute("nickname",member.getNickname());
        if (dto.getWriter() == member.getNickname()){
            return "/board/update";
        }else{
            return "redirect:/board/post/" +id;
        }

    }
    @GetMapping("/board/search")
    public String search(@PageableDefault(sort = "createDate",direction = Sort.Direction.DESC) Pageable pageable,
                         Model model , @RequestParam String keyword,@RequestParam String type,@AuthenticationPrincipal UserDetails user){
        model.addAttribute("boardList",postService.searchFind(type,keyword,pageable));
        model.addAttribute("pageList",postService.getSearchPageList(type, keyword, pageable));
        model.addAttribute("keyword",keyword);
        model.addAttribute("type",type);
        Member member = memberService.findOneByUsername(user.getUsername()).get();
        model.addAttribute("nickname",member.getNickname());
        return"/board/search_list";
    }





    /**
     *
     * 밑 부분은 restcontroller를 사용하려던 요청에 대한 api들을 모아놓았다.
     */

    @PostMapping("/api/posts")
    public String save(@Valid PostRequestDto dto, Errors errors,Model model){
        if(errors.hasErrors()){
            System.out.println(dto);
            model.addAttribute("dto",dto);
            Map<String,String> validateResult = postService.validateHandling(errors);
            for (String key : validateResult.keySet()){
                model.addAttribute(key,validateResult.get(key));
                System.out.println(key);
            }
            return "/board/write";
        }

        Long saveId = postService.save(dto);
        return "redirect:/board/list";
    }
    @PostMapping("/api/post/edit/{id}")
    public String update(@PathVariable Long id,PostRequestDto dto){
        Long update = postService.update(id, dto);
        return "redirect:/board/post/" + id;
    }

    @PostMapping("/api/post/delete/{id}")
    public String delete(@PathVariable Long id){
        postService.deleteOne(id);
        return "redirect:/board/list";
    }



    private boolean setCommonAttributes(Model model, Long id, UserDetails user) {
        PostResponseDto dto = postService.findOne(id);
        model.addAttribute("boardDto", dto);
        Member member = memberService.findOneByUsername(user.getUsername()).get();
        model.addAttribute("nickname", member.getNickname());

        if (dto.getWriter() == member.getNickname()){
            return true;
        }else{
            return false;
        }
    }




}
