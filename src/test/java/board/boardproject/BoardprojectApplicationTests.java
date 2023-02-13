package board.boardproject;

import board.boardproject.domain.Hello;
import board.boardproject.repository.HelloRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class BoardprojectApplicationTests {



	@Autowired
	HelloRepository helloRepository;



	@Test
	void contextLoads() {
	}

	@Test
	void save() {
		//given
		Hello item = new Hello("chanw");

		//when
		Hello savedHello = helloRepository.save(item);

		Hello findHello = helloRepository.findById(savedHello.getId()).get();
		Assertions.assertThat(savedHello.getName()).isEqualTo(findHello.getName());


	}

}
