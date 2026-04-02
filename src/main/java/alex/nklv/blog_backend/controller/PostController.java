package alex.nklv.blog_backend.controller;

import alex.nklv.blog_backend.dto.PostDto;
import alex.nklv.blog_backend.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<PostDto> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @GetMapping("/search")
    public List<PostDto> searchByTitle(@RequestParam String title) {
        return service.searchByTitle(title);
    }

    @PostMapping
    public PostDto create(@Valid @RequestBody PostDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PostDto update(@PathVariable Long id, @Valid @RequestBody PostDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}