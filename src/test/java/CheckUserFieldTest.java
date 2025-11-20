import action.CreateUser;
import data.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_FORBIDDEN;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class CheckUserFieldTest extends BaseTest {
    private final String email;
    private final String password;
    private final String name;


    public CheckUserFieldTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters(name = "Данные для теста: email={0}, password={1}, name={2}")
    public static Object[][] checkUser() {
        return new Object[][]{
                {"", PASSWORD, NAME},
                {EMAIL, "", NAME},
                {EMAIL, PASSWORD, ""}
        };
    }

    @Test
    @DisplayName("Checking User fields")
    @Description("Проверка API создание пользователя при незаполненых полях")
    public void checkFieldTest() {
        UserModel user = new UserModel(email, password, name);
        CreateUser createUser = new CreateUser();

        createUser.createUserApi(user)
                .then()
                .statusCode(HTTP_FORBIDDEN)
                .assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

}
