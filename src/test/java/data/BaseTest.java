package data;

import action.UserLogin;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.After;
import org.junit.Before;

import static action.DataAPI.BASEURL;
import static action.DataAPI.USER_DELETE;
import static data.TestData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BaseTest {
    private UserModel user;
    private UserLogin userLogin;
    private String token;

    @Before
    public void setup() {
        RestAssured.baseURI = BASEURL;
        user = new UserModel(EMAIL, PASSWORD, NAME);


    }

    @After  // удаляем пользователя
    public void userDelete() {
        userLogin = new UserLogin();
        Response loginResponse = userLogin.loginUserApi(user);
        token = userLogin.getAuthToken();

        if (token != null && !token.trim().isEmpty()) {


            given()
                    .log().all() // Логируем только при ошибке
                    .header("Authorization", token)
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(USER_DELETE)
                    .then()
                    .statusCode(equalTo(202))
                    .log().ifValidationFails(); // Логируем ответ при ошибке


        };
    }

}
