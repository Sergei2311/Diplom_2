import action.CreateUser;
import data.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateExistUser extends BaseTest {
    private UserModel user;
    Response response;
    CreateUser createUser = new CreateUser();

    @Test
    @DisplayName("Creating an existing user")
    @Description("Создать существующего пользователя")
    public void createExistUserTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        createUser.createUserApi(user);
        response = createUser.createUserApi(user)
                .then()
                .statusCode(HTTP_FORBIDDEN) // Проверяем код ответа
                .body("success", equalTo(false)) // Проверяем поле success
                .body("message", equalTo("User already exists")) // Проверяем message
                .extract().response();

    }

}
