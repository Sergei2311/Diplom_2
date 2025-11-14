import action.CreateUser;
import data.BaseTest;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest extends BaseTest implements CreateUser {
    private UserModel user;
    Response response;

    @Test
    @DisplayName("Creating user")
    @Step("Создать пользователя")
    public void createUserTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);

        response = createUserApi(user)
                .then()
                .log().all()
                .statusCode(HTTP_OK) // Проверяем код ответа
                .body("success", equalTo(true)) // Проверяем поле success
                .extract().response();
    }
}
