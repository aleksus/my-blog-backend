package alex.nklv.blog_backend.controller;

import alex.nklv.blog_backend.dto.PostRequestDto;
import alex.nklv.blog_backend.dto.PostResponseDto;
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
    public List<PostResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PostResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<PostResponseDto> getByUserId(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @GetMapping("/search")
    public List<PostResponseDto> searchByTitle(@RequestParam String title) {
        return service.searchByTitle(title);
    }

    @PostMapping
    public PostResponseDto create(@Valid @RequestBody PostRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PostResponseDto update(@PathVariable Long id, @Valid @RequestBody PostRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}