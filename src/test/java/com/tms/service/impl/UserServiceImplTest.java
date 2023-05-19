package com.tms.service.impl;

import java.util.List;
import java.util.Optional;

import com.tms.mapper.MappersFromUser;
import com.tms.mapper.MappersToUser;
import com.tms.model.domain.User;
import com.tms.model.request.user.UserRegistrationRequest;
import com.tms.model.response.user.UserGetByIdResponse;
import com.tms.repository.UserRepository;
import com.tms.utils.validation.service.CheckUserByIdInService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends TestCase {

    @Mock
    private  UserRepository userRepository;

    @Mock
    private  CheckUserByIdInService checkUserByIdInService;

    @Mock
    private  MappersToUser mappersToUser;

    @Mock
    private MappersFromUser mappersFromUser;

    @InjectMocks
    private UserServiceImpl userService;

    private final String LOGIN = "LOGIN";

    private final int ID = 1;

    @Test
    public void testDeleteUser() {
        Mockito.when(checkUserByIdInService.checkUserByIdAndType(1)).thenReturn(true);
        Mockito.when(userRepository.deleteUser(ID)).thenReturn(1);
        verify(userRepository,times(1)).deleteUser(ID);
    }

    @Test
    public void testUserRegistration() {
        UserRegistrationRequest userRegistrationRequest = Mockito.mock(UserRegistrationRequest.class);
        User user = Mockito.mock(User.class);
        when(mappersToUser.fromUserRegistrationRequestToUser(userRegistrationRequest,user)).thenReturn(user);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        User user2 =userRepository.saveAndFlush(user);
        verify(userRepository,times(1)).saveAndFlush(user);
        assertEquals(user.getLoginUser(),user2.getLoginUser());
    }

    @Test
    public void testGetUserById() {
        User user = Mockito.mock(User.class);
        user.setLoginUser(LOGIN);
        UserGetByIdResponse userGetByIdResponse = new UserGetByIdResponse();
        when(userRepository.findByIsDeleted(ID)).thenReturn(Optional.of(user));
        when(checkUserByIdInService.checkUserByIdAndType(ID)).thenReturn(true);
        mappersFromUser.userGetByIdResponseFromUser(userGetByIdResponse,user);
        Optional<User> usercheck = userRepository.findByIsDeleted(ID);
        verify(userRepository,times(1)).findByIsDeleted(ID);
        assertEquals(usercheck.get().getLoginUser(),user.getLoginUser());
    }

    @Test
    public void getAllUsers() {
        User user = Mockito.mock(User.class);
        when(checkUserByIdInService.checkUserByType()).thenReturn(true);
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> userFromMethod = userRepository.findAll();
        verify(userRepository,times(1)).findAll();
        assertNotNull(userFromMethod);
    }
}