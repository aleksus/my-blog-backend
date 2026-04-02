package alex.nklv.blog_backend.service;

import alex.nklv.blog_backend.dto.CommentDto;
import alex.nklv.blog_backend.entity.Comment;
import alex.nklv.blog_backend.entity.Post;
import alex.nklv.blog_backend.entity.User;
import alex.nklv.blog_backend.repository.CommentRepository;
import alex.nklv.blog_backend.repository.PostRepository;
import alex.nklv.blog_backend.repository.UserRepository;
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

    public List<CommentDto> getAll() {
        return commentRepository.findAll().stream().map(this::toDto).toList();
    }

    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return toDto(comment);
    }

    public List<CommentDto> getByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream().map(this::toDto).toList();
    }

    public List<CommentDto> getByUserId(Long userId) {
        return commentRepository.findByUserId(userId).stream().map(this::toDto).toList();
    }

    public CommentDto create(CommentDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setText(dto.getText());
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());

        return toDto(commentRepository.save(comment));
    }

    public CommentDto update(Long id, CommentDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        comment.setText(dto.getText());
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

    private CommentDto toDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getUser().getId(),
                comment.getUser().getName(),
                comment.getPost().getId(),
                comment.getPost().getTitle(),
                comment.getCreatedAt()
        );
    }
}