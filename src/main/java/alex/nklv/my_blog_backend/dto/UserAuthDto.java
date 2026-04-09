package alex.nklv.my_blog_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    private String password;
}
