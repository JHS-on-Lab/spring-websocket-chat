package me.son.springwebsocketchat.chat.domain.service;

import lombok.RequiredArgsConstructor;
import me.son.springwebsocketchat.chat.domain.entity.Message;
import me.son.springwebsocketchat.chat.domain.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> findMessages(Long roomId) {
        return messageRepository.findByRoomIdOrderByCreatedAtAsc(roomId);
    }
}
