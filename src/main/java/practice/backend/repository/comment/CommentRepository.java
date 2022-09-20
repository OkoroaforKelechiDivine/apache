package practice.backend.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.backend.model.comment.Comment;

import java.time.LocalDateTime;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {}
