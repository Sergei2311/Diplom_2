package action;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import static action.DataAPI.*;
import static io.restassured.RestAssured.given;

public class CreateUser {

    private String authToken; // Инкапсуляция
    private String authTokenFresh;

    @Step("Создание пользователя")
    public Response createUserApi(UserModel user) {
        Response response =  given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_CREATE);

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




}
