package alex.nklv.blog_backend.controller;

import alex.nklv.blog_backend.dto.UserDto;
import alex.nklv.blog_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}