package practice.backend.controller.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import practice.backend.dto.request.CreatePostDto;
import practice.backend.model.roleType.UserType;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private CreatePostDto createPostDto;

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJleHAiOjE2NjQ0NzIxNzN9.JI2o3zXZ_mDoHHHz4Z-G-wNxPGw-IjdNSJ2ExUEo3HIn4Ai4cB3w5U1KzrITdtFnMmYmPVKnEhAHu0qAGbNplQ";

    @BeforeEach
    public void startUpMethod(){
        objectMapper = new ObjectMapper();
        createPostDto = new CreatePostDto();
    }

    @Test
    @DisplayName("Create post")
    public void test_createPost() throws Exception {
        createPostDto.setContent("There is time for everyone.");
        createPostDto.setAuthor(UserType.ADMIN);
        createPostDto.setAdminId(1);

        this.mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createPostDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    @DisplayName("Delete post")
    public void test_deletePost() throws Exception {
        this.mockMvc.perform(delete("/posts/1/1")
                        .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}