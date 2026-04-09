package alex.nklv.blog_backend.service;

import alex.nklv.blog_backend.dto.PostRequestDto;
import alex.nklv.blog_backend.dto.PostResponseDto;
import alex.nklv.blog_backend.entity.Post;
import alex.nklv.blog_backend.entity.User;
import alex.nklv.blog_backend.repository.PostRepository;
import alex.nklv.blog_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostResponseDto> getAll() {
        return postRepository.findAll().stream().map(this::toDto).toList();
    }

    public PostResponseDto getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return toDto(post);
    }

    public List<PostResponseDto> getByUserId(Long userId) {
        return postRepository.findByUserId(userId).stream().map(this::toDto).toList();
    }

    public List<PostResponseDto> searchByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title).stream().map(this::toDto).toList();
    }

    public PostResponseDto create(PostRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUser(user);
        post.setImageUrl(dto.getImageUrl());

        return toDto(postRepository.save(post));
    }

    public PostResponseDto update(Long id, PostRequestDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUser(user);
        post.setImageUrl(dto.getImageUrl());

        return toDto(postRepository.save(post));
    }

    public void delete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    private PostResponseDto toDto(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getId(),
                post.getUser().getName(),
                post.getImageUrl(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
