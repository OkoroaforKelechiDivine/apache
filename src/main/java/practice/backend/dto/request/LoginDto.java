package practice.backend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.backend.model.roleType.UserType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    private String email;

    private String password;

    private UserType type;
}
