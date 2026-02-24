package me.son.springwebsocketchat.chat.controller;

import lombok.RequiredArgsConstructor;

import me.son.springwebsocketchat.chat.domain.entity.ChatRoom;
import me.son.springwebsocketchat.chat.domain.service.ChatRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/chat")
    public String chat(Model model, Principal principal) {

        List<ChatRoom> rooms = chatRoomService.findRoomsByUsername(principal.getName());

        model.addAttribute("rooms", rooms);

        return "chat";
    }
}
