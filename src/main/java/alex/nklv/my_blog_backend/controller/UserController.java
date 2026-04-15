package alex.nklv.my_blog_backend.controller;

import alex.nklv.my_blog_backend.dto.ChangePasswordDto;
import alex.nklv.my_blog_backend.dto.UserAuthDto;
import alex.nklv.my_blog_backend.dto.UserDto;
import alex.nklv.my_blog_backend.dto.UserRequestDto;
import alex.nklv.my_blog_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public UserDto create(@Valid @RequestBody UserRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @Valid @RequestBody UserRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/authenticate")
    public UserDto login(@Valid @RequestBody UserAuthDto dto) {
        return service.login(dto);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDto dto) {

        service.changePassword(id, dto);
        return ResponseEntity.ok().build();
    }
}