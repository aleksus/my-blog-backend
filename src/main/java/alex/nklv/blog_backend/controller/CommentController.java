package alex.nklv.blog_backend.controller;

import alex.nklv.blog_backend.dto.CommentDto;
import alex.nklv.blog_backend.service.CommentService;
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
    public List<CommentDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CommentDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/post/{postId}")
    public List<CommentDto> getByPostId(@PathVariable Long postId) {
        return service.getByPostId(postId);
    }

    @GetMapping("/user/{userId}")
    public List<CommentDto> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @PostMapping
    public CommentDto create(@Valid @RequestBody CommentDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CommentDto update(@PathVariable Long id, @Valid @RequestBody CommentDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}