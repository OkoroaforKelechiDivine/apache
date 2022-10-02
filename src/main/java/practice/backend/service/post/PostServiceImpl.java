package practice.backend.service.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.backend.dto.request.CreatePostDto;
import practice.backend.dto.request.UpdatePostDto;
import practice.backend.exception.BlogException;
import practice.backend.model.admin.Admin;
import practice.backend.model.comment.Comment;
import practice.backend.model.post.Post;
import practice.backend.model.roleType.UserType;
import practice.backend.repository.admin.AdminRepository;
import practice.backend.repository.post.PostRepository;
import practice.backend.repository.user.BlogUserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PostRepository postRepository;

    public Boolean postDoesNotExistById(Integer id) {
        return !postRepository.existsById(id);
    }

    public Post findById(int id) {
        return postRepository.findPostById(id);
    }

    public Boolean existsByAdminId(Integer id){
        return adminRepository.existsById(id);
    }

    @Override
    public Post createPost(CreatePostDto createPostDto) throws BlogException {
        Admin existingAdmin = adminRepository.findAdminById(createPostDto.getAdminId());

        if (createPostDto.getContent().equals("")){
            throw new BlogException("Post content can not be empty.");
        }
        Post post = new Post();
        post.setCreatedDate(LocalDateTime.now());
        post.setContent(createPostDto.getContent());
        post.setAuthor(createPostDto.getAuthor());
        post.setReactions(createPostDto.getReactions());
        post.setAdminId(createPostDto.getAdminId());

        if (post.getAuthor() == UserType.USER){
            throw new BlogException("Users are not allowed to create a post.");
        }
        if (!existsByAdminId(post.getAdminId())){
            throw new BlogException("This admin does not have an account with us.");
        }
        adminRepository.save(existingAdmin);
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(int id) {
        return postRepository.findPostById(id);
    }

    @Override
    public Post updatePost(UpdatePostDto updatePostDto) throws BlogException {
        Post existingPost = findById(updatePostDto.getId());

        if (postDoesNotExistById(updatePostDto.getId())){
            throw new BlogException("Post with id '" + updatePostDto.getId() + "' does not exist.");
        }
        if (!updatePostDto.getContent().equals("")){
            existingPost.setContent(updatePostDto.getContent());
        }
        existingPost.setCreatedDate(LocalDateTime.now());
        return postRepository.save(existingPost);
    }

    @Override
    public void deletePost(int postId, int adminId) throws BlogException {
        if (!existsByAdminId(adminId)){
            throw new BlogException("Admin with id '" + adminId + "' doesn't exists.");
        }
        if (postDoesNotExistById(postId)){
            throw new BlogException("Post doesn't exists.");
        }
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }
}
