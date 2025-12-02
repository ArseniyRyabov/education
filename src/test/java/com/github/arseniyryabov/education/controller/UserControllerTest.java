package com.github.arseniyryabov.education.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.arseniyryabov.education.controller.model.UserResponse;
import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.service.UsersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;

import java.util.Arrays;
import java.util.List;

public class UserControllerTest {

    @Mock
    private UsersService usersService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    //@BeforeEach public void setUp(){MockitoAnnotations.openMocks(this);}

    @AfterEach
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    @Test
    public void testGetById() throws Exception {
        Long userId = 1L;
        UserEntity userEntity = new UserEntity(userId, "Иванов", "Иван", "Иванович");
        when(usersService.getById(userId)).thenReturn(userEntity);

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Иван"));

        verify(usersService).getById(userId);
    }

    @Test
    public void testCreate() throws Exception {
        UserCreatingRequest userCreatingRequest = new UserCreatingRequest("Иванов", "Иван", "Иванович");
        Long userId = 1L;
        when(usersService.create(userCreatingRequest)).thenReturn(userId);

        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content("{\"userName\":\"Иван\", \"lastName\":\"Иванов\", \"secondName\":\"Иванович\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(userId.toString()));

        verify(usersService).create(userCreatingRequest);
    }

    @Test
    public void testGetWithSortingAndPaginationForFilter() throws Exception {
        UserResponse user1 = new UserResponse(1L, "Иван", "Иванов", "Иванович");
        UserResponse user2 = new UserResponse(2L, "Петр", "Петров", "Петрович");
        List<UserResponse> userList = Arrays.asList(user1, user2);

        when(usersService.getWithSortingAndPaginationForFilter(any(String.class), any(int.class), any(int.class))).thenReturn(userList);

        mockMvc.perform(get("/users")
                        .param("lastName", "LastName")
                        .param("limit", "10")
                        .param("offset", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value("Иван"))
                .andExpect(jsonPath("$[1].userName").value("Петр"));

        verify(usersService).getWithSortingAndPaginationForFilter("LastName", 10, 0);
    }
}