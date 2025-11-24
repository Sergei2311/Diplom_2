package data;

import action.CreateUser;
import action.UserLogin;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.UserModel;
import org.junit.After;
import org.junit.Before;

import static action.DataAPI.BASEURL;
import static action.DataAPI.USER_DELETE;
import static data.TestData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BaseTestWithCreateUser {
    private String userID;
    private UserModel user;
    CreateUser createUser;
    private String token;
    private String refreshToken;
    UserLogin userLogin;

    // BaseTest с созданием пользователя
    @Before
    public void setup() {
        RestAssured.baseURI = BASEURL;
        user = new UserModel(EMAIL, PASSWORD, NAME);
        createUser = new CreateUser();
        createUser.createUserApi(user);
        token = createUser.getAuthToken();
        userLogin = new UserLogin();
        userLogin.loginUserApi(user);
    }

    @After  // удаляем пользователя
    public void userDelete() {

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


        }
        ;
    }

}
