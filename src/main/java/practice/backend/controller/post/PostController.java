package practice.backend.controller.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.backend.dto.request.CreatePostDto;
import practice.backend.dto.response.ResponseDetails;
import practice.backend.exception.BlogException;
import practice.backend.service.post.PostServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/blog-user")
@Slf4j
@CrossOrigin(origins = "true", allowCredentials = "true")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@Valid @RequestBody CreatePostDto createPostDto,  HttpServletRequest httpServletRequest) throws BlogException {
        postService.createPost(createPostDto);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Your post has been shared successfully.", HttpStatus.OK.toString());
        return ResponseEntity.status(200).body(responseDetails);
    }

    @DeleteMapping("/{postId}/{adminId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId, @PathVariable int adminId) throws BlogException {
        postService.deletePost(postId, adminId);
        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Post has been deleted successfully.", "success");
        return new ResponseEntity<>(responseDetails, HttpStatus.OK);
    }
}
