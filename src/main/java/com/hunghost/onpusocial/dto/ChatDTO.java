package com.hunghost.onpusocial.dto;
import java.util.Set;

public class ChatDTO {
    private String owner;
    private String description;
    private String name;
    private Set<Long> members;
    private Long chatphoto;

    public ChatDTO() {
    }

    public Long getChatphoto() {
        return chatphoto;
    }

    public void setChatphoto(Long chatphoto) {
        this.chatphoto = chatphoto;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getMembers() {
        return members;
    }

    public void setMembers(Set<Long> members) {
        this.members = members;
    }
}
