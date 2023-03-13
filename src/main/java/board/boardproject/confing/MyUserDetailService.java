package board.boardproject.confing;

import board.boardproject.domain.Member;
import board.boardproject.domain.dto.MemberResponseDto;
import board.boardproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    public MyUserDetailService(MemberService memberService){
        this.memberService=memberService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.findOneByUsername(username).orElseThrow(()->new UsernameNotFoundException("등록되지 않은 회원입니다"));
        return User.builder().username(member.getUsername()).password(member.getPassword()).authorities(Collections.emptyList()).build();
    }


}
