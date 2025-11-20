import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.startsWith;

public class UserInputPositiveTest extends BaseTestWithCreateUser  {
    private UserModel user;
    Response response;
    UserLogin userLogin;

    @Test
    @DisplayName("Positive user input")
    @Description("Пользователя создали и залогинили")
    public void userInputPositiveTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        userLogin = new UserLogin();

        response = userLogin.loginUserApi(user)
                .then()
                .statusCode(HTTP_OK) // Проверяем код ответа
                .body("success", equalTo(true)) // Проверяем поле success
                .body("accessToken", startsWith("Bearer")) // Проверяем accessToken
                .extract().response();
    }

}
