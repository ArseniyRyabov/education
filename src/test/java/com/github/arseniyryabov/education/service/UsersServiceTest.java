package com.github.arseniyryabov.education.service;

import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;
import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsersServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    public void setUp() {
        try (var mocks = MockitoAnnotations.openMocks(this)) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateUser() {
        UserCreatingRequest request = new UserCreatingRequest();
        request.setLastName("Петров");
        request.setUserName("Петр");
        request.setSecondName("Петрович");

        UserEntity mockUserEntity = new UserEntity();
        mockUserEntity.setId(1L);
        mockUserEntity.setLastName(request.getLastName());
        mockUserEntity.setUserName(request.getUserName());
        mockUserEntity.setSecondName(request.getSecondName());

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(mockUserEntity);

        Long userId = usersService.create(request);

        assertEquals(1L, userId);
    }
}