package practice.backend.service.user;

import practice.backend.dto.request.CreateBloggerDto;
import practice.backend.dto.request.UpdateBloggerDto;
import practice.backend.exception.BlogException;
import practice.backend.model.user.BlogUser;

import java.util.List;

public interface BlogUserService {

    BlogUser createUser(CreateBloggerDto blogger) throws BlogException;

    BlogUser findUserById(int id);

    BlogUser findUserByEmail(String email);

    BlogUser updateUser(UpdateBloggerDto updateBloggerDto) throws BlogException;

    void deleteUser(int id);

    List<BlogUser> findAllUsers();
}
