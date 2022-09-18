package practice.backend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.admin.Admin;
import practice.backend.model.user.Blogger;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Integer> {

    Blogger findById(int id);

    boolean existsByEmail(String email);

    default void updatedBlogger(Blogger blogger){
        blogger.setCreatedDate(LocalDate.now());
        save(blogger);
    }

    default void deleteBloggerById(int id){
        deleteById(id);
    }

    List<Blogger> findAll();
}
