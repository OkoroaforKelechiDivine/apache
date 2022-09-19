package practice.backend.model.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.backend.model.roleType.UserType;
import practice.backend.model.user.BlogUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private UserType userType;

    private String username;

    private LocalDateTime createdDate;

    @OneToOne
    private BlogUser user;

    private Integer postId;
}
