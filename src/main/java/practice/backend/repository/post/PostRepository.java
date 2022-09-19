package practice.backend.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.post.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findPostById(Integer id);

    Post findPostByContent(String content);
}

