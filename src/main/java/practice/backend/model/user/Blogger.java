package practice.backend.model.user;

import practice.backend.model.roleType.Gender;
import practice.backend.model.roleType.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Gender gender;

    private UserType userType;
}
