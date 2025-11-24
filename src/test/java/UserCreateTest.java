import action.CreateUser;
import data.BaseTest;
import data.TestData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.Test;

import static action.DataAPI.EMAIL;
import static action.DataAPI.PASSWORD;
import static action.DataAPI.NAME;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;

// регистрация  пользователя, регистрация существующего пользователя
public class UserCreateTest extends BaseTest {
    private UserModel user;
    Response response;
    CreateUser createUser;


    @Test
    @DisplayName("Creating user")
    @Description("Создать пользователя")
    public void createUserTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        createUser = new CreateUser();

        response = createUser.createUserApi(user)
                .then()
                .log().all()
                .statusCode(HTTP_OK) // Проверяем код ответа
                .body("success", equalTo(true)) // Проверяем поле success
                .extract().response();
    }

    @Test
    @DisplayName("Creating an existing user")
    @Description("Создать существующего пользователя")
    public void createExistUserTest() {
        user = new UserModel(TestData.EMAIL, TestData.PASSWORD, TestData.NAME);
        createUser = new CreateUser();
        createUser.createUserApi(user);
        response = createUser.createUserApi(user)
                .then()
                .statusCode(HTTP_FORBIDDEN) // Проверяем код ответа
                .body("success", equalTo(false)) // Проверяем поле success
                .body("message", equalTo("User already exists")) // Проверяем message
                .extract().response();
    }


}
