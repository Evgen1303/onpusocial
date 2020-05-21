package com.hunghost.onpusocial.chats;

import com.hunghost.onpusocial.entity.ServerFile;

import java.time.LocalDateTime;


public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
    private Long chatId;
    private ServerFile serverFile;

    private LocalDateTime localDateTime = LocalDateTime.now();
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
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

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public ServerFile getServerFile() {
        return serverFile;
    }

    public void setServerFile(ServerFile serverFile) {
        this.serverFile = serverFile;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}