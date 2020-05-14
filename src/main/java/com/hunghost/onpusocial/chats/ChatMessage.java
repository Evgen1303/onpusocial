package com.hunghost.onpusocial.chats;

import java.time.LocalDateTime;


public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
    private Long chatId;

    private LocalDateTime localDateTime = LocalDateTime.now();
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}