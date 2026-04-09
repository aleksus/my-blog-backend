package alex.nklv.my_blog_backend.controller;

import alex.nklv.my_blog_backend.dto.CommentRequestDto;
import alex.nklv.my_blog_backend.dto.CommentResponseDto;
import alex.nklv.my_blog_backend.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<CommentResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CommentResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponseDto> getByPostId(@PathVariable Long postId) {
        return service.getByPostId(postId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentResponseDto> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public CommentResponseDto create(@Valid @RequestBody CommentRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CommentResponseDto update(@PathVariable Long id, @Valid @RequestBody CommentRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}