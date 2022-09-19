package practice.backend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.user.BlogUser;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Integer> {

    BlogUser findBlogUserById(Integer id);

    BlogUser findByEmail(String email);

    default void updatedBlogger(BlogUser blogUser){
        blogUser.setCreatedDate(LocalDate.now());
        save(blogUser);
    }

    default void deleteBloggerById(int id){
        deleteById(id);
    }

    List<BlogUser> findAll();

    Boolean existsByEmail(String email);
}
