package net.codetemplate.uda.domain;

import static net.codetemplate.uda.domain.UserRepository.NotFoundException;

class LoginInteractor {
    private final UserRepository userRepository;
    private final LoginPresenter loginPresenter;

    public LoginInteractor(UserRepository userRepository, LoginPresenter loginPresenter) {
        this.userRepository = userRepository;
        this.loginPresenter = loginPresenter;
    }

    public void execute(LoginRequest loginRequest) {
        User user = userRepository.findBy(loginRequest.getId());
        if(user == null)
            throw new NotFoundException(loginRequest.getId());
        user.login(loginRequest.getPassword());
        LoginResponse loginResponse = new LoginResponse(user.getId());
        loginPresenter.present(loginResponse);
    }
}
