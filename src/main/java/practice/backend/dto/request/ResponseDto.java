package practice.backend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.backend.model.roleType.Gender;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private Integer id;

    private String token;

    private String username;

    private String phoneNumber;

    private String email;

    private Boolean isVerified;

    private String createdDate;

    private Gender sex;

    private String verificationToken;

    private Boolean isActive;

    private String modifiedDate;
}