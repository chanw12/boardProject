package board.boardproject.service;


import board.boardproject.domain.Comment;
import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import board.boardproject.domain.dto.CommentReqDto;
import board.boardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService extends CommonService{

    private final MemberService memberService;
    private final PostService postService;
    private final CommentRepository commentRepository;


    public Long save(Long post_id,Long member_id, CommentReqDto dto){
        Member findMember = memberService.findOneByUserId(member_id).get();
        dto.setMember(findMember);
        Post findPost = postService.findOne_By_Postid(post_id);
        dto.setPost(findPost);
        Comment comment = dto.toEntity();
        commentRepository.save(comment);

        return comment.getId();

    }
    public Page<Comment> findAll_By_Postid(Long postid, Pageable pageable){
        Page<Comment> allByPostId = commentRepository.findAllByPostId(postid, pageable);
        return allByPostId;
    }

}
