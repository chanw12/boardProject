package board.boardproject.domain;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER,TALK,LEAVE
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;



}
