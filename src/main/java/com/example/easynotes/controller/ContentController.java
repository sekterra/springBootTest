package com.example.easynotes.controller;

import com.example.easynotes.repository.ContentRepository;
import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContentController {
	@Autowired
	ContentRepository contentRepository;

	// Get All Notes
	@GetMapping("/notes")
	public List<Content> getAllNotes() {
		return contentRepository.findAll();
	}

	// Create a new Note
	@PostMapping(path = "/notes")
	public Content createNote(@Valid @RequestBody Content content) {
		return contentRepository.save(content);
	}

	// Get a Single Note
	@GetMapping("/notes/{id}")
	public Content getNoteById(@PathVariable(value = "id") Long contentId) {
		return contentRepository.findById(contentId)
						.orElseThrow(() -> new ResourceNotFoundException("Note", "id", contentId));
	}

	// Update a Note
	@PutMapping("/notes/{id}")
	public Content updateNote(@PathVariable(value = "id") Long contentId,
	                       @Valid @RequestBody Content contentDetails) {

		Content content = contentRepository.findById(contentId)
						.orElseThrow(() -> new ResourceNotFoundException("Content", "id", contentId));

		content.setTitle(contentDetails.getTitle());
		content.setContent(contentDetails.getContent());

		Content updatedContent = contentRepository.save(content);
		return updatedContent;
	}

	// Delete a Note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long contentId) {
		Content content = contentRepository.findById(contentId)
						.orElseThrow(() -> new ResourceNotFoundException("Content", "id", contentId));

		contentRepository.delete(content);

		return ResponseEntity.ok().build();
	}
}
