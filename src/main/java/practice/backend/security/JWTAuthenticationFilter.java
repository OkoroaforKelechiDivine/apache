package practice.backend.security;


import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import practice.backend.dto.request.LoginDto;
import practice.backend.dto.request.ResponseDto;
import practice.backend.dto.request.UnsuccessfulLogin;
import practice.backend.exception.BlogException;
import practice.backend.model.admin.Admin;
import practice.backend.model.roleType.UserType;
import practice.backend.model.user.BlogUser;
import practice.backend.repository.admin.AdminRepository;
import practice.backend.repository.user.BlogUserRepository;
import org.springframework.context.ApplicationContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.auth0.jwt.JWT;

import static practice.backend.security.SecurityConstants.*;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final BlogUserRepository userRepository;
    private final AdminRepository adminRepository;

    LoginDto credential = new LoginDto();

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext context) {
        this.authenticationManager = authenticationManager;
        userRepository = context.getBean(BlogUserRepository.class);
        adminRepository = context.getBean(AdminRepository.class);
        setFilterProcessesUrl("/user/login");
    }

    @Override
    @ExceptionHandler(BlogException.class)
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            credential = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getEmail(), credential.getPassword(), new ArrayList<>()));
        } catch (IOException exception) {
            throw new RuntimeException("Bad credential. User does not exist.");
        }
    }

    @Override
    @ExceptionHandler(BlogException.class)
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String token = JWT.create()
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes(StandardCharsets.UTF_8)));
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto responseDto;
        String email = ((User) authResult.getPrincipal()).getUsername();
        BlogUser user = userRepository.findByEmail(email);

        if (credential.getType() != null && credential.getType().equals(UserType.ADMIN)) {
            Admin admin = adminRepository.findByUser(user);
            if (admin == null) {
                throw new javax.security.sasl.AuthenticationException("Admin details should not be empty.");
            }
            responseDto = new ResponseDto();
            responseDto.setId(admin.getId());
            responseDto.setCreatedDate(admin.getCreatedDate().toString());
            responseDto.setUsername(user.getUsername());
            responseDto.setPhoneNumber(user.getEmail());
            responseDto.setToken(token);
        }
        else {
            BlogUser blogUser = userRepository.findBlogUserById(user.getId());
            responseDto = new ResponseDto();
            responseDto.setEmail(blogUser.getEmail());
            responseDto.setCreatedDate(LocalDateTime.now().toString());
            responseDto.setUsername(blogUser.getUsername());
            responseDto.setId(blogUser.getId());
            responseDto.setModifiedDate(LocalDateTime.now().toString());
            responseDto.setModifiedDate(LocalDateTime.now().toString());
            responseDto.setPhoneNumber(blogUser.getPhoneNumber());
            responseDto.setSex(blogUser.getGender());
            responseDto.setToken(token);
        }
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.getOutputStream().print("{ \"Object data\": " + objectMapper.writeValueAsString(responseDto) + "}");
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        UnsuccessfulLogin responseDetails = new UnsuccessfulLogin(LocalDateTime.now(), "Login error. Incorrect email or password.", "Bad request", "/user/login");
        response.getOutputStream().print("{ \"Server response\": " + responseDetails + "}");
    }
}
