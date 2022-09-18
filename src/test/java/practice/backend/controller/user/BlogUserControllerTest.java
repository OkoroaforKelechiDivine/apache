package practice.backend.controller.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import practice.backend.dto.request.CreateBloggerDto;
import practice.backend.dto.request.LoginDto;
import practice.backend.model.roleType.Gender;
import practice.backend.model.roleType.UserType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest

public class BlogUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    LoginDto loginDto;

    private ObjectMapper objectMapper;

    private CreateBloggerDto registerDto;

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2Z0d2FyZWVuZ2luZWVyQGdtYWlsLmNvbSIsImV4cCI6MTY2NDIzMjUwMn0.zTH38McZvvc4JwA-YIkf4DMCtSgZgOCAvn544dk52PJGWThf0jwT-CqOfPLFGhsZhx__8Z8BoNsD0D-IFYxwKQ";

    @BeforeEach
    public void startUpMethod() {
        objectMapper = new ObjectMapper();
        registerDto = new CreateBloggerDto();
        loginDto = new LoginDto();
    }

    @Test
    @DisplayName("Create account")
    public void test_createUserAccount() throws Exception {
        registerDto.setPassword("zip it now or never");
        registerDto.setUsername("zipDemon");
        registerDto.setGender(Gender.MALE);
        registerDto.setRoleType(UserType.USER);
        registerDto.setEmail("softwareengineer@gmail.com");

        this.mockMvc.perform(post("/blog-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Login")
    public void test_userLogin() throws Exception {
        loginDto.setPassword("zip it now or never");
        loginDto.setEmail("softwareengineer@gmail.com");
        this.mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}