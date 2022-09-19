package practice.backend.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.backend.model.admin.Admin;
import practice.backend.model.roleType.Gender;
import practice.backend.model.roleType.UserType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private String username;

    private Gender gender;

    private UserType userType;

    private LocalDateTime modifiedDate;

    private String password;

    private String phoneNumber;

    private String email;

    private LocalDate createdDate;

}
