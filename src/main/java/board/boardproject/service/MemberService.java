package board.boardproject.service;

import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import board.boardproject.domain.dto.MemberRequestDto;
import board.boardproject.domain.dto.MemberResponseDto;
import board.boardproject.domain.dto.PostRequestDto;
import board.boardproject.domain.dto.PostResponseDto;
import board.boardproject.repository.MemberRepository;
import board.boardproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;



    @Transactional
    public Long save(MemberRequestDto dto){
        Member member = dto.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Optional<Member> findOneByUsername(String username){

        return memberRepository.findByUsername(username);
    }

}
