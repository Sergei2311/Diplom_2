package action;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import static action.DataAPI.*;
import static io.restassured.RestAssured.given;

public interface CreateUser {

    @Step("Создание пользователя")
    public default Response createUserApi(UserModel user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_CREATE);

    }
}
