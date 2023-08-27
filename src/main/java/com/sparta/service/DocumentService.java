package com.sparta.service;


import com.sparta.dto.DocumentRequestDto;
import com.sparta.dto.DocumentResponseDto;
import com.sparta.entity.Document;
import com.sparta.repository.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentResponseDto createDocument(DocumentRequestDto requestDto) {
        Document document = new Document(requestDto);
        Document savedDocument = this.documentRepository.save(document);
        return new DocumentResponseDto(savedDocument);
    }

    public List<DocumentResponseDto> getEveryDocument() {
        return this.documentRepository.findAllByOrderByModifiedAtDesc().stream().map(DocumentResponseDto::new).toList();
//        return this.documentRepository.findAll().stream().map(DocumentResponseDto::new).toList();
    }

    public DocumentResponseDto getSingleDocument(Long id) {
        Document document = this.findDocument(id);
        return new DocumentResponseDto(document);
    }

    @Transactional
    public DocumentResponseDto updateDocument(Long id, DocumentRequestDto requestDto) {
        Document document = this.findDocument(id);
        if (requestDto.getPassword().equals(document.getPassword())) {
            document.update(requestDto);
            return new DocumentResponseDto(document);
        } else {
            throw new IllegalArgumentException("Invalid Password");
        }
    }

    public String deleteDocument(Long id, DocumentRequestDto requestDto) {
        Document document = this.findDocument(id);
        if (requestDto.getPassword().equals(document.getPassword())) {
            this.documentRepository.delete(document);
            return "{\"success\": true}";
        } else {
            throw new IllegalArgumentException("Invalid Password");
        }
    }

    private Document findDocument(Long id) {
        return this.documentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Given ID is not valid"));
    }
}
