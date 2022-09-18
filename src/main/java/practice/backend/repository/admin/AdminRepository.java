package practice.backend.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.admin.Admin;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findById(int postId);

    boolean existsByEmail(String adminEmail);

    default void updatedAdmin(Admin admin){
        admin.setCreatedDate(LocalDate.now());
        save(admin);
    }

    default void deleteAdminById(int id){
        deleteById(id);
    }

    List<Admin> findAll();
}
