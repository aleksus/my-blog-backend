package alex.nklv.blog_backend.service;

import alex.nklv.blog_backend.dto.PostDto;
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

    public List<PostDto> getAll() {
        return postRepository.findAll().stream().map(this::toDto).toList();
    }

    public PostDto getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return toDto(post);
    }

    public List<PostDto> getByUserId(Long userId) {
        return postRepository.findByUserId(userId).stream().map(this::toDto).toList();
    }

    public List<PostDto> searchByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title).stream().map(this::toDto).toList();
    }

    public PostDto create(PostDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        return toDto(postRepository.save(post));
    }

    public PostDto update(Long id, PostDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setUser(user);

        return toDto(postRepository.save(post));
    }

    public void delete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    private PostDto toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getId(),
                post.getUser().getName(),
                post.getCreatedAt()
        );
    }
}
