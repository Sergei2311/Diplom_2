package action;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import static action.DataAPI.*;
import static io.restassured.RestAssured.given;

public class UserLogin {

    private String authToken; // Инкапсуляция
    private String authTokenFresh;
    String requestBody = String.format("{\"token\": \"%s\"}", authTokenFresh);


    @Step("Залогиниться")
    public Response loginUserApi(UserModel user) {
        Response response = given()
                .log().ifValidationFails() // Логируем только при ошибке
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_LOGIN);

        // Извлекаем токен из ответа
        this.authToken = response.jsonPath().getString("accessToken");
        this.authTokenFresh = response.jsonPath().getString("refreshToken");


        return response;
    }

    @Step("Получить токен обновления и удаления данных")
    public String getAuthToken() {
        return authToken;
    }
    @Step("Получить токен выхода из системы")
    public String getAuthTokenFresh() {
        return authTokenFresh;
    }
    @Step("Выход из системы")
    public Response logout (){
        return given()
                .log().ifValidationFails()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .when()
                .post(ORDER_LOGOUT);

    }
}
