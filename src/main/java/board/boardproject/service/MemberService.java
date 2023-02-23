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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Long save(MemberRequestDto dto){
        MemberRequestDto pwencodedDto = new MemberRequestDto(dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getNickname());
        Member member = pwencodedDto.toEntity();
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Optional<Member> findOneByUsername(String username){

        return memberRepository.findByUsername(username);
    }

}
