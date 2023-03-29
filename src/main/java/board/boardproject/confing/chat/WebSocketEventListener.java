package board.boardproject.confing.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpUserRegistry simpUserRegistry;
    private final SimpMessagingTemplate messagingTemplate;
    private final SimpMessageHeaderAccessor simpMessageHeaderAccessor = SimpMessageHeaderAccessor.create();

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        Principal user = event.getUser();
        System.out.println(user);
        String destination = simpMessageHeaderAccessor.getDestination();
        if (destination!=null){
            System.out.println("---------------");
            System.out.println(destination);
            List<String> users = getCollect(destination);
            messagingTemplate.convertAndSend(destination,users);
        }
        System.out.println("유저가 접속했스빈다------------");

    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String destination = simpMessageHeaderAccessor.getDestination();
        // 유저가 접속을 끊었을 때, 모든 유저에게 접속 정보를 전송한다.
        if (destination!=null){
            List<String> users = getCollect(destination);
            messagingTemplate.convertAndSend(destination,users);

        }
        System.out.println("유저가 해제했스빈다------------");
    }

    private List<String> getCollect(String destination) {
        System.out.println(simpUserRegistry.getUsers());
        return simpUserRegistry.getUsers().stream()
                .filter(user -> simpUserRegistry.getUser(user.getName())
                        .getSessions().stream()
                        .anyMatch(session -> session.getSubscriptions().stream()
                                .anyMatch(subscription -> subscription.getDestination().equals(destination))))
                .map(user -> user.getName())
                .collect(Collectors.toList());
    }
}
