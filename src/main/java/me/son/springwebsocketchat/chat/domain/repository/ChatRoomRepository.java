package me.son.springwebsocketchat.chat.domain.repository;

import me.son.springwebsocketchat.chat.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
