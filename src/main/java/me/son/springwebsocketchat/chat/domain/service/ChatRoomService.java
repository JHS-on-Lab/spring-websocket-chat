package me.son.springwebsocketchat.chat.domain.service;

import lombok.RequiredArgsConstructor;
import me.son.springwebsocketchat.chat.domain.entity.ChatRoom;
import me.son.springwebsocketchat.chat.domain.entity.ChatRoomMember;
import me.son.springwebsocketchat.chat.domain.repository.ChatRoomMemberRepository;
import me.son.springwebsocketchat.chat.domain.repository.ChatRoomRepository;
import me.son.springwebsocketchat.user.domain.entity.User;
import me.son.springwebsocketchat.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomMemberRepository chatRoomMemberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public List<ChatRoom> findRoomsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return chatRoomMemberRepository.findRoomsByUser(user);
    }

    @Transactional
    public void createRoom(String roomName, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();

        ChatRoom room = ChatRoom.builder()
                .name(roomName)
                .build();

        chatRoomRepository.save(room);

        ChatRoomMember member = ChatRoomMember.builder()
                .chatRoom(room)
                .user(user)
                .build();

        chatRoomMemberRepository.save(member);
    }

    public boolean isMember(Long roomId, String username) {
        return chatRoomMemberRepository.existsByChatRoomIdAndUserUsername(roomId, username);
    }
}
