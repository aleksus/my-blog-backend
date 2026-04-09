package alex.nklv.my_blog_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long id;

    private String content;

    private Long userId;

    private String authorName;

    private Long postId;

    private String postTitle;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}