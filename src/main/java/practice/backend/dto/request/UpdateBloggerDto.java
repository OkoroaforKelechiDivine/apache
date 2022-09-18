package practice.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBloggerDto {

    private int id;

    private String email;

    private String password;

    private String username;

    private String phoneNumber;
}
