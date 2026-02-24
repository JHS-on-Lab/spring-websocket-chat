package me.son.springwebsocketchat.chat.controller;

import lombok.RequiredArgsConstructor;

import me.son.springwebsocketchat.chat.domain.entity.Message;
import me.son.springwebsocketchat.chat.domain.repository.MessageRepository;
import me.son.springwebsocketchat.chat.domain.service.ChatRoomService;
import me.son.springwebsocketchat.chat.dto.ChatMessageRequest;
import me.son.springwebsocketchat.chat.dto.ChatMessageResponse;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send")
    public void send(ChatMessageRequest request, Principal principal) {
        Message message = Message.builder()
                .roomId(request.roomId())
                .sender(principal.getName())
                .content(request.content())
                .build();

        messageRepository.save(message);

        messagingTemplate.convertAndSend(
                "/topic/room." + request.roomId(),
                new ChatMessageResponse(
                        message.getSender(),
                        message.getContent(),
                        request.roomId()
                )
        );
    }

}
