package board.boardproject.web;


import board.boardproject.confing.chat.WebSocketConfig;
import board.boardproject.domain.ChatMessage;
import board.boardproject.domain.ChatRoom;
import board.boardproject.domain.Member;
import board.boardproject.repository.ChatRoomRepository;
import board.boardproject.service.ChatService;
import board.boardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;

import java.lang.reflect.Array;
import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;
    private final MemberService memberService;
    private final SimpUserRegistry simpUserRegistry;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model, @AuthenticationPrincipal UserDetails user) {
        Member member = memberService.findOneByUsername(user.getUsername()).get();
        model.addAttribute("nickname",member.getNickname());
        model.addAttribute("chatRooms",chatService.findAllRoom());


        return "board/chatroomlist";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room")
    public String createRoom(@RequestParam("name") String name) {
        chatService.createRoom(name);
        return "redirect:/chat/room";
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter")
    public String roomDetail(Model model, @RequestParam String roomId,@AuthenticationPrincipal UserDetails user) {
        Member member = memberService.findOneByUsername(user.getUsername()).get();
        ChatRoom findChatRoom = chatService.findRoomById(roomId);
        model.addAttribute("nickname",member.getNickname());
        model.addAttribute("chatRoom",findChatRoom);
        System.out.println(findChatRoom);
        return "board/chatroom";
    }





}