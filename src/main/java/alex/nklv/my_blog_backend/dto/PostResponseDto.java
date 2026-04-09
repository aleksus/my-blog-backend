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
public class PostResponseDto {

    private Long id;

    private String title;

    private String content;

    private Long userId;

    private String authorName;

    private String imageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}