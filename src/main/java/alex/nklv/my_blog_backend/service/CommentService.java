package alex.nklv.my_blog_backend.service;

import alex.nklv.my_blog_backend.dto.CommentRequestDto;
import alex.nklv.my_blog_backend.dto.CommentResponseDto;
import alex.nklv.my_blog_backend.entity.Comment;
import alex.nklv.my_blog_backend.entity.Post;
import alex.nklv.my_blog_backend.entity.User;
import alex.nklv.my_blog_backend.repository.CommentRepository;
import alex.nklv.my_blog_backend.repository.PostRepository;
import alex.nklv.my_blog_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository,
                          UserRepository userRepository,
                          PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<CommentResponseDto> getAll() {
        return commentRepository.findAll().stream().map(this::toDto).toList();
    }

    public CommentResponseDto getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return toDto(comment);
    }

    public List<CommentResponseDto> getByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream().map(this::toDto).toList();
    }

    public List<CommentResponseDto> getByUserId(Long userId) {
        return commentRepository.findByUserId(userId).stream().map(this::toDto).toList();
    }

    public CommentResponseDto create(CommentRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        return toDto(commentRepository.save(comment));
    }

    public CommentResponseDto update(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        comment.setContent(dto.getContent());
        comment.setUser(user);
        comment.setPost(post);

        return toDto(commentRepository.save(comment));
    }

    public void delete(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found");
        }
        commentRepository.deleteById(id);
    }

    private CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getUser().getName(),
                comment.getPost().getId(),
                comment.getPost().getTitle(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}