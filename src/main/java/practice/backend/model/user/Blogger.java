package practice.backend.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
