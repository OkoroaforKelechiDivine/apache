package practice.backend.model.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.backend.model.post.Post;
import practice.backend.model.roleType.Gender;
import practice.backend.model.roleType.UserType;
import practice.backend.model.user.BlogUser;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    private LocalDate createdDate;

    private Gender gender;

    private UserType userType;

    @OneToOne
    private BlogUser user;

    @OneToMany
    private List<Post> post;
}
