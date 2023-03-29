package board.boardproject.service;

import board.boardproject.domain.Member;
import board.boardproject.domain.dto.MemberRequestDto;
import board.boardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService extends CommonService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public List<Member> findAllByUsername(List<String> usernames){
        List<Member> members = new ArrayList<>();
        for(String username : usernames){
            Member member = memberRepository.findByUsername(username).get();
            members.add(member);
        }
        return members;
    }

    @Transactional
    public Optional<Member> findOneByNickname(String nickname){
        return memberRepository.findByNickname(nickname);
    }

    @Transactional
    public Long save(MemberRequestDto dto){
        MemberRequestDto pwencodedDto = new MemberRequestDto(dto.getUsername(), passwordEncoder.encode(dto.getPassword()), dto.getNickname());
        Member member = pwencodedDto.toEntity();
        if (memberRepository.existsByNickname(member.getNickname()))
        {
            throw new IllegalStateException("이미 존재하는 닉네임입니다");
        }else if(memberRepository.existsByUsername(member.getUsername())){
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }
        else{
            memberRepository.save(member);
        }

        return member.getId();
    }

    @Transactional
    public Optional<Member> findOneByUsername(String username){

        return memberRepository.findByUsername(username);
    }

    @Transactional
    public Optional<Member> findOneByUserId(Long userid){

        return memberRepository.findById(userid);
    }


}
