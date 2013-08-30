package net.codetemplate.uda.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

@RunWith(Enclosed.class)
public class LoginInteractorTest {
    static String id = "id";
    static String pwd = "pwd";

    public static class GivenValidCollaborators {
        UserRepository userRepository = mock(UserRepository.class);
        LoginPresenter loginPresenter = mock(LoginPresenter.class);
        User user = spy(new User(id));
        LoginRequest loginRequest;
        LoginInteractor loginInteractor;

        @Before
        public void setUp() {
            when(userRepository.findBy(id)).thenReturn(user);
            doNothing().when(user).login(pwd);
            loginInteractor = new LoginInteractor(userRepository, loginPresenter);
        }

        @Test
        public void dummy() {
        }
    }

    public static class GivenValidIdPwd extends GivenValidCollaborators {
        @Before
        public void setup() {
            loginRequest = new LoginRequest(id, pwd);
        }

        void verifySuccessCondition() {
            verify(userRepository, times(1)).findBy(id);
            verify(user, times(1)).login(pwd);
            verify(loginPresenter, times(1)).present((LoginResponse) anyObject());
        }

        @Test
        public void loginSuccessfully() {
            loginInteractor.execute(loginRequest);

            verifySuccessCondition();
        }
    }

    public static class GivenInvalidId extends GivenValidCollaborators {
        @Before
        public void setup() {
            loginRequest = new LoginRequest("wrongId", pwd);
        }

        @Test(expected = UserRepository.NotFoundException.class)
        public void whenLoginTried_thenThrowNotFound() {
            loginInteractor.execute(loginRequest);
        }
    }

    public static class GivenInvalidPassword extends GivenValidCollaborators {
        @Before
        public void setup() {
            loginRequest = new LoginRequest(id, "wrongPwd");
            doThrow(User.WrongPassword.class).when(user).login("wrongPwd");
        }

        @Test(expected = User.WrongPassword.class)
        public void whenLoginTried_thenThrowNotFound() {
            loginInteractor.execute(loginRequest);
        }
    }
}
