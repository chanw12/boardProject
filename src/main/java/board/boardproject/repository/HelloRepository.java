package board.boardproject.repository;


import board.boardproject.domain.Hello;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@Transactional
public class HelloRepository {
    private final EntityManager em;

    public HelloRepository(EntityManager em) {
        this.em = em;
    }

    public Hello save(Hello hello) {
        em.persist(hello);
        return hello;
    }

    public Optional<Hello> findById(Long id) {
        Hello hello = em.find(Hello.class, id);
        return Optional.ofNullable(hello);
    }


}


