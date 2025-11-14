import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;

public class UserInputPositiveTest extends BaseTestWithCreateUser implements UserLogin {
    private UserModel user;
    Response response;

    @Test
    @DisplayName("Positive user input")
    @Step("Пользователя создали и залогинили")
    public void userInputPositiveTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);

        response = loginUserApi(user)
                .then()
                .statusCode(HTTP_OK) // Проверяем код ответа
                .body("success", equalTo(true)) // Проверяем поле success
                .body("accessToken", startsWith("Bearer")) // Проверяем accessToken
                .extract().response();
    }

}
