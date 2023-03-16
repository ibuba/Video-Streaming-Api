package miu.videokabbee.service.UserServiceImpl;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import miu.videokabbee.domain.Token;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.TokenServiceInterface;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private TokenServiceInterface tokenServiceInterface;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#logOut(HttpServletRequest)}
     */
    @Test
    void testLogOut() {
        doNothing().when(tokenServiceInterface).create((Token) any());
        userServiceImpl.logOut(new MockHttpServletRequest());
        verify(tokenServiceInterface).create((Token) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#logOut(HttpServletRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLogOut2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "jakarta.servlet.http.HttpServletRequest.getHeader(String)" because "request" is null
        //       at miu.videokabbee.service.UserServiceImpl.UserServiceImpl.logOut(UserServiceImpl.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(tokenServiceInterface).create((Token) any());
        userServiceImpl.logOut(null);
    }

    /**
     * Method under test: {@link UserServiceImpl#logOut(HttpServletRequest)}
     */
    @Test
    void testLogOut3() {
        doNothing().when(tokenServiceInterface).create((Token) any());
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getHeader((String) any())).thenReturn("https://example.org/example");
        userServiceImpl.logOut(httpServletRequestWrapper);
        verify(tokenServiceInterface).create((Token) any());
        verify(httpServletRequestWrapper).getHeader((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#logOut(HttpServletRequest)}
     */
    @Test
    void testLogOut4() {
        doNothing().when(tokenServiceInterface).create((Token) any());
        HttpServletRequestWrapper httpServletRequestWrapper = mock(HttpServletRequestWrapper.class);
        when(httpServletRequestWrapper.getHeader((String) any())).thenReturn("Bearer ");
        userServiceImpl.logOut(httpServletRequestWrapper);
        verify(tokenServiceInterface).create((Token) any());
        verify(httpServletRequestWrapper).getHeader((String) any());
    }
}

