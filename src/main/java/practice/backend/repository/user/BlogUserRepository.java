package practice.backend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.user.BlogUser;

import java.util.List;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {

    BlogUser findUserById(Integer id);

    BlogUser findByEmail(String email);

    List<BlogUser> findAll();

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);
}
