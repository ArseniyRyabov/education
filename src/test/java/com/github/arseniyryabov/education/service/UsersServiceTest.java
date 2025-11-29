package com.github.arseniyryabov.education.service;

import com.github.arseniyryabov.education.controller.model.UserCreatingRequest;
import com.github.arseniyryabov.education.controller.model.UserResponse;
import com.github.arseniyryabov.education.entity.UserEntity;
import com.github.arseniyryabov.education.exceptions.UserNotFoundException;
import com.github.arseniyryabov.education.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        verify(userRepository, times(1)).save(any(UserEntity.class));

        assertEquals(1L, userId);
    }

    @Test
    public void testGetUserById_UserExists() {
        UserEntity userEntity = new UserEntity("Иванов", "Иван", "Иванович");
        userEntity.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));

        UserResponse userResponse = usersService.getUserById(1L);

        assertNotNull(userResponse);
        assertEquals("Иван", userResponse.getUserName());
        assertEquals("Иванов", userResponse.getLastName());
        assertEquals("Иванович", userResponse.getSecondName());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            usersService.getUserById(2L);
        });

        assertEquals("Пользователь с ID 2 не найден", exception.getMessage());
    }


}