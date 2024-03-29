package board.boardproject.web;

import board.boardproject.domain.Comment;
import board.boardproject.domain.Member;
import board.boardproject.domain.dto.CommentReqDto;
import board.boardproject.repository.CommentRepository;
import board.boardproject.repository.MemberRepository;
import board.boardproject.service.CommentService;
import board.boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;
    private final MemberService memberService;

    @PostMapping("/api/comment")
    public String comment_register(@RequestParam("postid") Long postid, @RequestParam("nickname") String nickname
            , CommentReqDto dto, RedirectAttributes re){
        Member findmember = memberService.findOneByNickname(nickname).get();
        commentService.save(postid,findmember.getId(),dto);

        return "redirect:/board/post/" + postid;

    }

    @PostMapping("/api/comment/edit")
    public String comment_edit(@RequestParam("comment_id") Long commentid,@RequestParam("postid") Long postid,CommentReqDto dto){
        commentService.update(commentid, dto);

        return "redirect:/board/post/" +postid;
    }
    @PostMapping("/api/comment/delete")
    public String comment_delete(@RequestParam("comment_id") Long commentid,@RequestParam("postid") Long postid){
        commentService.delete(commentid);
        return "redirect:/board/post/"+ postid;
    }
}
