import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class UserInputNegativeTest extends BaseTestWithCreateUser  {
    private final String email;
    private final String password;
    private final String name;

    public UserInputNegativeTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters(name = "Данные для теста: email={0}, password={1}, name={2}")
    public static Object[][] checkUser() {
        return new Object[][]{
                {"", PASSWORD, NAME},
                {EMAIL, "", NAME},
                {"testSergt@tesst.ru", PASSWORD, NAME},
                {EMAIL, "yuyyu", NAME}
        };
    }

    @Test
    @DisplayName("User login with a missing username or password")
    @Description("Вход с отсутствующим или несуществующим логином или паролем")
    public void checkFieldTest() {
        UserModel user = new UserModel(email, password, name);
        UserLogin userlogin = new UserLogin();
        userlogin.loginUserApi(user)
                .then()
                .statusCode(HTTP_UNAUTHORIZED)  // Проверяем код ответа
                .assertThat().body("message", equalTo("email or password are incorrect")); // Проверяем поле message
    }

}
