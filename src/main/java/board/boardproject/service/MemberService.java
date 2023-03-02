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
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService extends CommonService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Optional<Member> findOneByNickname(String nickname){
        return memberRepository.findByNickname(nickname);
    }

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
