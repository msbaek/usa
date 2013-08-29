package net.codetemplate.uda.domain;

public class LoginRequest {
    private String id;
    private String password;

    public LoginRequest(String id, String pwd) {
        this.id = id;
        this.password = pwd;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
