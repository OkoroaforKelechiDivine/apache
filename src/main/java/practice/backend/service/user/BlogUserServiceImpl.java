package practice.backend.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import practice.backend.dto.request.RegisterUserDto;
import practice.backend.dto.request.UpdateUserDto;
import practice.backend.exception.BlogException;
import practice.backend.model.admin.Admin;
import practice.backend.model.roleType.UserType;
import practice.backend.model.user.BlogUser;
import practice.backend.repository.admin.AdminRepository;
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

    @Autowired
    private AdminRepository adminRepository;

    public BlogUser findById(int id){
        return blogUserRepository.findUserById(id);
    }

    public Boolean userDoesNotExistById(int id){
        return !blogUserRepository.existsById(id);
    }

    private String encryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public Boolean alreadyExistByUsername(String username){
        return blogUserRepository.existsByUsername(username);
    }
    
    public Boolean existsByAdminUsername(String username){
        return adminRepository.existsByUsername(username);
    }

    public Boolean existByEmail(String email){
        return blogUserRepository.existsByEmail(email);
    }
   
    @Override
    public BlogUser createUser(RegisterUserDto user) throws BlogException {
        if (Objects.equals(user.getEmail(), "")){
            throw new BlogException("User email is empty.");
        }
        if (!user.getEmail().contains("@")){
            throw new BlogException("Invalid user email.");
        }
        if (user.getPassword().length() < 5){
            throw new BlogException("User password should not be less than 5 characters.");
        }
        BlogUser blogUser = new BlogUser();
        blogUser.setCreatedDate(LocalDate.now());
        blogUser.setEmail(user.getEmail());
        blogUser.setGender(user.getGender());
        blogUser.setUsername(user.getUsername());
        blogUser.setUserType(user.getRoleType());
        blogUser.setPassword(encryptPassword(user.getPassword()));

        if (alreadyExistByUsername(blogUser.getUsername())){
            throw new BlogException("User with username '" + blogUser.getUsername() +  "' already exists.");
        }
        Admin admin = new Admin();
        admin.setUserType(blogUser.getUserType());
        admin.setUsername(user.getUsername());
        admin.setCreatedDate(LocalDateTime.now());

        if (admin.getUserType().equals(UserType.ADMIN)) {
            if (existsByAdminUsername(admin.getUsername())){
                throw new BlogException("An admin with username '" + admin.getUsername() + "' already exist.");
            }
            adminRepository.save(admin);
        }
        return blogUserRepository.save(blogUser);
    }

    @Override
    public BlogUser findUserById(int id) {
        return blogUserRepository.findUserById(id);
    }

    @Override
    public BlogUser findUserByEmail(String email) {
        return blogUserRepository.findByEmail(email);
    }

    @Override
    public BlogUser updateUser(UpdateUserDto updateUserDto) throws BlogException {
        BlogUser existingUser = findById(updateUserDto.getId());

        if (userDoesNotExistById(updateUserDto.getId())){
            throw new BlogException("User with that id doesn't exist.");
        }
        if (updateUserDto.getEmail() != null){
            existingUser.setEmail(updateUserDto.getEmail());
        }
        if (updateUserDto.getUsername() != null){
            existingUser.setUsername(updateUserDto.getUsername());
        }
        if (updateUserDto.getPhoneNumber() != null){
            existingUser.setPhoneNumber(updateUserDto.getPhoneNumber());
        }
        existingUser.setModifiedDate(LocalDateTime.now());
        return blogUserRepository.save(existingUser);
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
