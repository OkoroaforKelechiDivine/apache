package practice.backend.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.backend.dto.request.CreateBloggerDto;
import practice.backend.dto.request.UpdateBloggerDto;
import practice.backend.exception.BlogException;
import practice.backend.model.roleType.UserType;
import practice.backend.model.user.Blogger;
import practice.backend.repository.user.BloggerRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class BloggerServiceImpl implements BloggerService{

    @Autowired
    private BloggerRepository bloggerRepository;

    public Blogger findById(int id){
        return bloggerRepository.findById(id);
    }

    public Boolean userDoesNotExistById(int id){
        return !bloggerRepository.existsById(id);
    }

    @Override
    public Blogger createUser(CreateBloggerDto newUser) throws BlogException {

        if (Objects.equals(newUser.getEmail(), "")){
            throw new BlogException("User email is empty");
        }
        if (!newUser.getEmail().contains("@")){
            throw new BlogException("Invalid user email");
        }
        if (newUser.getPassword().length() < 5){
            throw new BlogException("User password should not be less than 5 characters");
        }
        Blogger blogger = new Blogger();
        blogger.setCreatedDate(LocalDate.now());
        blogger.setEmail(newUser.getEmail());
        blogger.setGender(newUser.getGender());
        blogger.setUserType(UserType.USER);
        blogger.setPassword(newUser.getPassword());
        return bloggerRepository.save(blogger);
    }

    @Override
    public Blogger findUserById(int id) {
        return bloggerRepository.findById(id);
    }

    @Override
    public Blogger findUserByEmail(String email) {
        return bloggerRepository.findByEmail(email);
    }

    @Override
    public Blogger updateUser(UpdateBloggerDto updateBloggerDto) throws BlogException {
        Blogger existingUser = findById(updateBloggerDto.getId());

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
        bloggerRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(int id) {
        bloggerRepository.deleteById(id);
    }

    @Override
    public List<Blogger> findAllUsers() {
        return bloggerRepository.findAll();
    }
}
