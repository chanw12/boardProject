package board.boardproject.repository;

import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void clear(){
        memberRepository.deleteAll();
    }

    @Test
    public void createAndSavePost(){
        Member member = new Member("안녕하새요 가입했어요","안녕하세요 강찬우 라고 합니다 ","chanw12");
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        assertThat(member.getId()).isEqualTo(findMember.getId());

    }
}