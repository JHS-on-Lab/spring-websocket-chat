package me.son.springwebsocketchat.chat.controller;

import lombok.RequiredArgsConstructor;
import me.son.springwebsocketchat.chat.domain.entity.ChatRoom;
import me.son.springwebsocketchat.chat.domain.service.ChatRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatPageController {

    private final ChatRoomService chatRoomService;

    // 채팅방 목록 페이지
    @GetMapping("/chat")
    public String chatList(Model model, Principal principal) {
        List<ChatRoom> rooms = chatRoomService.findRoomsByUsername(principal.getName());
        model.addAttribute("rooms", rooms);
        return "chat-list";
    }

    // 채팅방 생성
    @PostMapping("/chat/rooms")
    public String createRoom(@RequestParam String name, Principal principal) {
        chatRoomService.createRoom(name, principal.getName());
        return "redirect:/chat";
    }

    // 실제 채팅방 페이지
    @GetMapping("/chat/{roomId}")
    public String chatRoom(@PathVariable Long roomId, Principal principal, Model model) {

        // 접근 권한 체크
        if (!chatRoomService.isMember(roomId, principal.getName())) {
            return "redirect:/chat";
        }

        model.addAttribute("roomId", roomId);
        return "chat-room";
    }
}