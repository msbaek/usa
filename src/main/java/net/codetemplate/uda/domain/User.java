package net.codetemplate.uda.domain;

public class User {
    private String id;

    public User(String id) {
        this.id = id;
    }

    public void login(String password) {
        throw new IllegalStateException("net.codetemplate.uda.domain.User#login not implemented yet");
    }

    public String getId() {
        return id;
    }
}
