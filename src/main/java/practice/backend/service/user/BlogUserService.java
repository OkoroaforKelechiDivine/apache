package practice.backend.service.user;

import practice.backend.dto.request.RegisterUserDto;
import practice.backend.dto.request.UpdateUserDto;
import practice.backend.exception.BlogException;
import practice.backend.model.user.BlogUser;

import java.util.List;

public interface BlogUserService {

    BlogUser createUser(RegisterUserDto blogger) throws BlogException;

    BlogUser findUserById(int id);

    BlogUser findUserByEmail(String email);

    BlogUser updateUser(UpdateUserDto updateUserDto) throws BlogException;

    void deleteUser(int id);

    List<BlogUser> findAllUsers();
}
