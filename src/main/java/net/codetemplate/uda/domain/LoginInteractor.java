package net.codetemplate.uda.domain;

class LoginInteractor {
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
