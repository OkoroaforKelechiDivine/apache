package practice.backend.service.user;

import practice.backend.dto.request.CreateBloggerDto;
import practice.backend.dto.request.UpdateBloggerDto;
import practice.backend.exception.BlogException;
import practice.backend.model.user.Blogger;

import java.util.List;

public interface BloggerService {

    Blogger createUser(CreateBloggerDto blogger) throws BlogException;

    Blogger findUserById(int id);

    Blogger findUserByEmail(String email);

    Blogger updateUser(UpdateBloggerDto updateBloggerDto) throws BlogException;

    void deleteUser(int id);

    List<Blogger> findAllUsers();
}
