package me.son.springwebsocketchat.chat.controller;

import lombok.RequiredArgsConstructor;
import me.son.springwebsocketchat.chat.domain.service.ChatRoomService;
import me.son.springwebsocketchat.chat.domain.service.MessageService;
import me.son.springwebsocketchat.chat.dto.ChatMessageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatRestController {
    private final MessageService messageService;
    private final ChatRoomService chatRoomService;

    @GetMapping("/{roomId}/messages")
    public List<ChatMessageResponse> getMessages(@PathVariable Long roomId, Principal principal) {

        // 접근 권한 체크
        if (!chatRoomService.isMember(roomId, principal.getName())) {
            throw new RuntimeException("Forbidden");
        }

        return messageService.findMessages(roomId)
                .stream()
                .map(m -> new ChatMessageResponse(
                        m.getSender(),
                        m.getContent(),
                        m.getRoomId()
                ))
                .toList();
    }
}
