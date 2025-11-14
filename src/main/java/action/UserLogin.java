package action;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import static action.DataAPI.USER_LOGIN;
import static io.restassured.RestAssured.given;

public interface UserLogin {

    @Step("Вход пользователя")
    public default Response loginUserApi(UserModel user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_LOGIN);
    }
}
