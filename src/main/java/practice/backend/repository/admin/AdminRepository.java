package practice.backend.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.admin.Admin;
import practice.backend.model.user.BlogUser;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findAdminById(Integer id);

    List<Admin> findAll();

    boolean existsByUsername(String username);

    Admin findByUser(BlogUser user);
}
