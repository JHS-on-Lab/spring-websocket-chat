package me.son.springwebsocketchat.chat.dto;

public record ChatMessageResponse(
        String sender,
        String content,
        Long roomId
) {}
