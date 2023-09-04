package com.sparta.controller;


import com.sparta.dto.DocumentRequestDto;
import com.sparta.dto.DocumentResponseDto;
import com.sparta.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DocumentController {
    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // Getting every document
    // param: None
    // return: List<DocumentResponseDto>
    @GetMapping("/posts")
    public List<DocumentResponseDto> getEveryDocument() {
        return this.documentService.getEveryDocument();
    }

    // Getting single document
    // param: @PathVariable id
    // return: DocumentResponseDto
    @GetMapping("/post/{id}")
    public DocumentResponseDto getSingleDocument(@PathVariable Long id) {
        return this.documentService.getSingleDocument(id);
    }

    // Creating a document
    // param: @RequestBody DocumentRequestDto requestDto
    // return: DocumentResponseDto
    @PostMapping("/post")
    public DocumentResponseDto createDocument(@RequestBody DocumentRequestDto requestDto) {
        return this.documentService.createDocument(requestDto);
    }

    // Edit a document
    // param: @PathVariable Long id, @RequestBody DocumentRequestDto requestDto
    // return: DocumentResponseDto responseDto
    @PutMapping("/post/{id}")
    public DocumentResponseDto updateDocument(@PathVariable Long id, @RequestBody DocumentRequestDto requestDto) {
        return this.documentService.updateDocument(id, requestDto);
    }

    // Delete a document
    // param @PathVariable Long id
    // return success message
    @DeleteMapping("/post/{id}")
    public String deleteDocument(@PathVariable Long id, @RequestBody DocumentRequestDto requestDto) {
        return this.documentService.deleteDocument(id, requestDto);
    }
}
