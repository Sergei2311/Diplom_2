package model;

import io.qameta.allure.Step;

public class UserModel {
    private String email;
    private String password;
    private String name;

    public UserModel(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserModel() {
    }
    @Step("Получить email")
    public String getEmail() {
        return email;
    }
    @Step("Создать email")
    public void setEmail(String email) {
        this.email = email;
    }
    @Step("Получить пароль")
    public String getPassword() {
        return password;
    }
    @Step("Создать пароль")
    public void setPassword(String password) {
        this.password = password;
    }
    @Step("Получить Имя")
    public String getName() {
        return name;
    }
    @Step("Создать имя")
    public void setName(String name) {
        this.name = name;
    }
}
