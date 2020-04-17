package com.hunghost.onpusocial.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Entity
@Table(name = "files")
public class ServerFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String filename;

    private String filetype;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", nullable = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User fileowner;

    @Lob
    private byte[] data;

    @Override
    public String toString() {
        return "ServerFile{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", filetype='" + filetype + '\'' +
                ", fileowner=" + fileowner +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public User getFileowner() {
        return fileowner;
    }

    public void setFileowner(User fileowner) {
        this.fileowner = fileowner;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
