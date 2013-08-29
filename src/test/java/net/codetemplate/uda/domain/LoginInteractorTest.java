package net.codetemplate.uda.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginInteractorTest {

    private final String id = "id";
    private final String pwd = "pwd";
    @Mock private UserRepository userRepository;
    @Mock private LoginPresenter loginPresenter;
    private User user = spy(new User(id));
    private LoginRequest loginRequest;

    @Before
    public void setUp() {
        loginRequest = new LoginRequest(id, pwd);

        doNothing().when(user).login(pwd);
        when(userRepository.findBy(id)).thenReturn(user);
    }

    private void verifySuccessCondition() {
        verify(userRepository, times(1)).findBy(id);
        verify(user, times(1)).login(pwd);
        verify(loginPresenter, times(1)).present((LoginResponse) anyObject());
    }

    @Test
    public void loginSuccessfully() {
        new LoginInteractor(userRepository, loginPresenter).execute(loginRequest);

        verifySuccessCondition();
    }

    private class LoginInteractor {
        private final UserRepository userRepository;
        private final LoginPresenter loginPresenter;

        public LoginInteractor(UserRepository userRepository, LoginPresenter loginPresenter) {
            this.userRepository = userRepository;
            this.loginPresenter = loginPresenter;
        }

        public void execute(LoginRequest loginRequest) {
            User user = userRepository.findBy(loginRequest.getId());
            user.login(loginRequest.getPassword());
            LoginResponse loginResponse = new LoginResponse(user.getId());
            loginPresenter.present(loginResponse);
        }
    }
}
