package miu.videokabbee.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import jakarta.servlet.http.HttpServletRequest;
import miu.videokabbee.service.UserServiceImpl.TokenService;
import miu.videokabbee.service.UserServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class UserControllerTest {

    @Test
    void testLogout() {


        UserServiceImpl userInterfaceService = new UserServiceImpl();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserController userController = new UserController(userInterfaceService, passwordEncoder, new TokenService());
        ResponseEntity<String> actualLogoutResult = userController.logout(new MockHttpServletRequest());
        assertEquals("Could not log out your account is still active", actualLogoutResult.getBody());
        assertEquals(400, actualLogoutResult.getStatusCodeValue());
        assertTrue(actualLogoutResult.getHeaders().isEmpty());
    }


    @Test
    void testLogout3() {


        UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);
        doNothing().when(userServiceImpl).logOut((HttpServletRequest) any());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserController userController = new UserController(userServiceImpl, passwordEncoder, new TokenService());
        ResponseEntity<String> actualLogoutResult = userController.logout(new MockHttpServletRequest());
        assertEquals("logged out successfully", actualLogoutResult.getBody());
        assertEquals(200, actualLogoutResult.getStatusCodeValue());
        assertTrue(actualLogoutResult.getHeaders().isEmpty());
        verify(userServiceImpl).logOut((HttpServletRequest) any());
    }
}

