package com.sparta.dto;

import com.sparta.entity.Document;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocumentResponseDto {
    private Long id;
    private String title;
    private String writer;
    private String password;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public DocumentResponseDto(Document doc) {
        this.id = doc.getId();
        this.title = doc.getTitle();
        this.writer = doc.getWriter();
        this.password = doc.getPassword();
        this.content = doc.getContent();
        this.createdAt = doc.getCreatedAt();
        this.modifiedAt = doc.getModifiedAt();
    }
}
