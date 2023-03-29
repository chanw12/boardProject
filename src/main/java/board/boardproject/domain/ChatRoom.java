package board.boardproject.domain;

import board.boardproject.service.ChatService;
import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
public class ChatRoom {
    @Id
    private String roomId;
    private String name;

    @Builder
    public ChatRoom(String roomId,String name,List<String> members){
        this.roomId = roomId;
        this.name = name;
    }

}
