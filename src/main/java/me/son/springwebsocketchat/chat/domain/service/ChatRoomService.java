package me.son.springwebsocketchat.chat.domain.service;

import lombok.RequiredArgsConstructor;
import me.son.springwebsocketchat.chat.domain.entity.ChatRoom;
import me.son.springwebsocketchat.chat.domain.repository.ChatRoomMemberRepository;
import me.son.springwebsocketchat.user.domain.entity.User;
import me.son.springwebsocketchat.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final UserRepository userRepository;

    public List<ChatRoom> findRoomsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow();
        return chatRoomMemberRepository.findRoomsByUser(user);
    }
}
