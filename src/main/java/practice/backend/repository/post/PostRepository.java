package practice.backend.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.post.Post;

import java.time.LocalDate;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findById(int id);

    default void updatePost(Post post){
        post.setCreatedDate(LocalDate.now());
        save(post);
    }

    Post findPostByContent(String postContent);


}

