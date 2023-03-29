package board.boardproject.domain.dto;

import lombok.Getter;

@Getter
public class ChatDto {
    public enum ChatLoginType{
        ENTER,LEAVE
    }
    private String sender;
    private String roomId;
    private ChatLoginType type;
}
