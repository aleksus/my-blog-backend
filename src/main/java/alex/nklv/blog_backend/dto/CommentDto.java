package alex.nklv.blog_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;

    @NotBlank(message = "Comment text is required")
    @Size(min = 2, max = 2000)
    private String text;

    @NotNull(message = "User ID is required")
    private Long userId;

    private String authorName;

    @NotNull(message = "Post ID is required")
    private Long postId;

    private String postTitle;

    private LocalDateTime createdAt;
}