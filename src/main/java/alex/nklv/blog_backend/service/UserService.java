package alex.nklv.blog_backend.service;

import alex.nklv.blog_backend.dto.UserDto;
import alex.nklv.blog_backend.entity.User;
import alex.nklv.blog_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<UserDto> getAll() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    public UserDto getById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toDto(user);
    }

    public UserDto create(UserDto dto) {
        repo.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email already exists");
        });

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return toDto(repo.save(user));
    }

    public UserDto update(Long id, UserDto dto) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return toDto(repo.save(user));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        repo.deleteById(id);
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}