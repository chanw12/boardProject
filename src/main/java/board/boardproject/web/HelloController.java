package board.boardproject.web;

import board.boardproject.domain.dto.LoginFormDto;
import board.boardproject.domain.dto.PostRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HelloController {

    @PostMapping("/api/auth/login")
    public String login(@RequestBody LoginFormDto data){
        return "ok";

    }


}
