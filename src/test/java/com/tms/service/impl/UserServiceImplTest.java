package com.tms.service.impl;

import java.util.Optional;

import com.tms.mapper.MappersFromUser;
import com.tms.mapper.MappersToUser;
import com.tms.model.domain.User;
import com.tms.model.request.User.UserRegistrationRequest;
import com.tms.model.response.User.UserGetByIdResponse;
import com.tms.repository.UserRepository;
import com.tms.utils.validation.service.CheckUserByIdInService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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



//
//    @Test
//    public void testDeleteUser() {
//        Mockito.when(checkUserByIdInService.checkUserByIdAndType(1)).thenReturn(true);
//        Mockito.when(userRepository.deleteUser(1)).thenReturn(1);
//    }
//
//    @Before
//    public void setUp() {
//        Mockito.mockitoSession()
//                .initMocks(this)
//                .strictness(Strictness.STRICT_STUBS)
//                .startMocking();
//    }

    @Test
    public void testUserRegistration() {
        UserRegistrationRequest userRegistrationRequest = Mockito.mock(UserRegistrationRequest.class);
        User user = Mockito.mock(User.class);
        when(mappersToUser.fromUserRegistrationRequestToUser(userRegistrationRequest,user)).thenReturn(user);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        User user2 =userRepository.saveAndFlush(user);
        assertEquals(user.getLoginUser(),user2.getLoginUser());

    }

    @Test
    public void testGetUserById() {
        User user = Mockito.mock(User.class);
        user.setLoginUser("asdasd");
        User use2r = Mockito.mock(User.class);
        UserGetByIdResponse userGetByIdResponse = new UserGetByIdResponse();
        when(userRepository.findByIs_deleted(1)).thenReturn(Optional.of(user));
        when(checkUserByIdInService.checkUserByIdAndType(1)).thenReturn(true);
        mappersFromUser.userGetByIdResponseFromUser(userGetByIdResponse,user);
        Optional<User> usercheck = userRepository.findByIs_deleted(1);
        System.out.println(usercheck.get().getLoginUser());
        assertEquals(usercheck.get().getLoginUser(),user.getLoginUser());

    }
}