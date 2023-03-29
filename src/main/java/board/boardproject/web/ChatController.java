package board.boardproject.web;


import board.boardproject.domain.ChatMessage;
import board.boardproject.domain.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final SimpUserRegistry simpUserRegistry;
    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        if (ChatMessage.MessageType.ENTER.equals(message.getType())){
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }
        if(ChatMessage.MessageType.LEAVE.equals(message.getType()))
        {
            message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
        }
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(),message);

    }
    @MessageMapping("/users")
    public void users(ChatDto dto){
        if(dto.getType() == ChatDto.ChatLoginType.LEAVE)
        {
            List<String> users = getCollect("/sub/users/" + dto.getRoomId());
            users.remove(dto.getSender());
            messagingTemplate.convertAndSend("/sub/users/"+dto.getRoomId(),users);
        }
        else{
            List<String> users = getCollect("/sub/users/" + dto.getRoomId());
            messagingTemplate.convertAndSend("/sub/users/"+dto.getRoomId(),users);
        }

    }

    private List<String> getCollect(String destination) {
        return simpUserRegistry.getUsers().stream()
                .filter(user -> simpUserRegistry.getUser(user.getName())
                        .getSessions().stream()
                        .anyMatch(session -> session.getSubscriptions().stream()
                                .anyMatch(subscription -> subscription.getDestination().equals(destination))))
                .map(user -> user.getName())
                .collect(Collectors.toList());
    }


}
