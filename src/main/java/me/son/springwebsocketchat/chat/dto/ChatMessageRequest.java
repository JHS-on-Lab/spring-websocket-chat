package me.son.springwebsocketchat.chat.dto;

public record ChatMessageRequest(
        Long roomId,
        String content
) {}
