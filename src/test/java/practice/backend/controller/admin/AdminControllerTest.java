package practice.backend.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import practice.backend.dto.request.LoginDto;
import practice.backend.dto.request.RegisterAdminDto;
import practice.backend.dto.request.RegisterUserDto;
import practice.backend.model.roleType.Gender;
import practice.backend.model.roleType.UserType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest

public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    LoginDto loginDto;

    private ObjectMapper objectMapper;

    private RegisterAdminDto registerDto;

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzb2Z0d2FyZWVuZ2luZWVyQGdtYWlsLmNvbSIsImV4cCI6MTY2NDM5MDIwMn0.XV1bBlPmhKR-wT1Y4HYgv-y5v0ZUi5P6cxQMEGd5xID_Q0APMso3gklBYDpemEOdJmavmTI2e3S2TCJQFjw7BA";

    @BeforeEach
    public void startUpMethod() {
        objectMapper = new ObjectMapper();
        registerDto = new RegisterAdminDto();
        loginDto = new LoginDto();
    }

    @Test
    @DisplayName("Create admin account")
    public void test_createAdminAccount() throws Exception {
        registerDto.setPassword("I am an admin");
        registerDto.setUsername("admin password");
        registerDto.setGender(Gender.FEMALE);
        registerDto.setRoleType(UserType.ADMIN);
        registerDto.setEmail("adminemail@gmail.com");

        this.mockMvc.perform(post("/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Admin login")
    public void test_adminLogin() throws Exception {
        loginDto.setPassword("I am an admin");
        loginDto.setEmail("adminemail@gmail.com");
        this.mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }


}