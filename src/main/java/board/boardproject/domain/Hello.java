package board.boardproject.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@Getter
public class Hello {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Hello(String name) {
        this.id = id;
        this.name = name;
    }
}
