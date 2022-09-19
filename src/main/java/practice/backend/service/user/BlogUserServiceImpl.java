package practice.backend.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import practice.backend.dto.request.RegisterUserDto;
import practice.backend.dto.request.UpdateBloggerDto;
import practice.backend.exception.BlogException;
import practice.backend.model.roleType.UserType;
import practice.backend.model.user.BlogUser;
import practice.backend.repository.user.BlogUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BlogUserServiceImpl implements BlogUserService {

    @Autowired
    private BlogUserRepository blogUserRepository;

    public BlogUser findById(int id){
        return blogUserRepository.findById(id);
    }

    public Boolean userDoesNotExistById(int id){
        return !blogUserRepository.existsById(id);
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public Boolean existByEmail(String email){
        return blogUserRepository.existsByEmail(email);
    }
    @Override
    public BlogUser createUser(RegisterUserDto newUser) throws BlogException {

        if (Objects.equals(newUser.getEmail(), "")){
            throw new BlogException("User email is empty");
        }
        if (!newUser.getEmail().contains("@")){
            throw new BlogException("Invalid user email");
        }
        if (newUser.getPassword().length() < 5){
            throw new BlogException("User password should not be less than 5 characters");
        }
        BlogUser blogUser = new BlogUser();
        blogUser.setCreatedDate(LocalDate.now());
        blogUser.setEmail(newUser.getEmail());
        blogUser.setGender(newUser.getGender());
        blogUser.setUserType(UserType.USER);
        blogUser.setPassword(encryptPassword(newUser.getPassword()));
        return blogUserRepository.save(blogUser);
    }

    @Override
    public BlogUser findUserById(int id) {
        return blogUserRepository.findById(id);
    }

    @Override
    public BlogUser findUserByEmail(String email) {
        return blogUserRepository.findByEmail(email);
    }

    @Override
    public BlogUser updateUser(UpdateBloggerDto updateBloggerDto) throws BlogException {
        BlogUser existingUser = findById(updateBloggerDto.getId());

        if (userDoesNotExistById(updateBloggerDto.getId())){
            throw new BlogException("User with that id doesn't exist");
        }
        if (updateBloggerDto.getEmail() != null){
            existingUser.setEmail(updateBloggerDto.getEmail());
        }
        if (updateBloggerDto.getUsername() != null){
            existingUser.setUsername(updateBloggerDto.getUsername());
        }
        if (updateBloggerDto.getPhoneNumber() != null){
            existingUser.setPhoneNumber(updateBloggerDto.getPhoneNumber());
        }
        existingUser.setModifiedDate(LocalDateTime.now());
        blogUserRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(int id) {
        blogUserRepository.deleteById(id);
    }

    @Override
    public List<BlogUser> findAllUsers() {
        return blogUserRepository.findAll();
    }
}
