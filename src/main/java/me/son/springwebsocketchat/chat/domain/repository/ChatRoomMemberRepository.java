package me.son.springwebsocketchat.chat.domain.repository;

import me.son.springwebsocketchat.chat.domain.entity.ChatRoom;
import me.son.springwebsocketchat.chat.domain.entity.ChatRoomMember;
import me.son.springwebsocketchat.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    @Query("""
       select m.chatRoom
       from ChatRoomMember m
       where m.user = :user
       """)
    List<ChatRoom> findRoomsByUser(@Param("user") User user);

    List<ChatRoomMember> findByUser(User user);

    List<ChatRoomMember> findByChatRoom(ChatRoom chatRoom);

    boolean existsByChatRoomIdAndUserUsername(Long roomId, String username);
}
