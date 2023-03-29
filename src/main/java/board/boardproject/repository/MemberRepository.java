package board.boardproject.repository;

import board.boardproject.domain.Member;
import board.boardproject.domain.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findById(String id);

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
