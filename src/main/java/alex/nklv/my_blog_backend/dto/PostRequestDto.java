package alex.nklv.my_blog_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200)
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 5000)
    private String content;

    @NotNull(message = "User ID is required")
    private Long userId;

    private String authorName;

    private String imageUrl;
}