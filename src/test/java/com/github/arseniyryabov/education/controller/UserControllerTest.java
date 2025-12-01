package com.github.arseniyryabov.education.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.repository.UserRepository;
import com.github.arseniyryabov.education.service.UsersService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;

import java.util.Arrays;
import java.util.List;

public class UserControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private UserRepository userRepository;

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
    public void testGetSortingAndPagination() throws Exception {
        String lastName = "Иванов";
        int limit = 10;
        int offset = 0;
        List<UserEntity> users = Arrays.asList(
                new UserEntity(1L, "Иванов", "Иван", "Иванович"),
                new UserEntity(2L, "Петров", "Петр", "Петрович")
        );
        when(userRepository.findWithSortingAndPagination(lastName, limit, offset)).thenReturn(users);

        mockMvc.perform(get("/users")
                        .param("lastName", lastName)
                        .param("limit", String.valueOf(limit))
                        .param("offset", String.valueOf(offset)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userName").value("Иван"));

        verify(userRepository).findWithSortingAndPagination(lastName, limit, offset);
    }
}