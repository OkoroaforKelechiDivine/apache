//package practice.backend.controller.user;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import practice.backend.dto.request.RegisterUserDto;
//import practice.backend.dto.response.ResponseDetails;
//import practice.backend.exception.BlogException;
//import practice.backend.service.user.BlogUserServiceImpl;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.time.LocalDateTime;
//
//@RestController
//@RequestMapping("/blog-user")
//@Slf4j
//@CrossOrigin(origins = "true", allowCredentials = "true")
//public class BlogUserController {
//
//    @Autowired
//    private BlogUserServiceImpl userService;
//
//    @PostMapping("")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto, HttpServletRequest httpServletRequest) throws BlogException {
//        if (!userService.existByEmail(registerUserDto.getEmail())){
//            userService.createUser(registerUserDto);
//        }
//        else {
//            throw new BlogException("User with that email already exist.");
//        }
//        ResponseDetails responseDetails = new ResponseDetails(LocalDateTime.now(), "Account created successfully", HttpStatus.OK.toString());
//        return ResponseEntity.status(200).body(responseDetails);
//    }
//
//}
