package practice.backend.service.post;

import practice.backend.dto.request.CreatePostDto;
import practice.backend.dto.request.UpdatePostDto;
import practice.backend.exception.BlogException;
import practice.backend.model.post.Post;

import java.util.List;

public interface PostService {

    Post createPost(CreatePostDto createPostDto) throws BlogException;

    Post findPostById(int id);

    Post updatePost(UpdatePostDto updatePostDto) throws BlogException;

    void deletePost(int postId, int adminId) throws BlogException;

    List<Post> findAllPosts();
}
