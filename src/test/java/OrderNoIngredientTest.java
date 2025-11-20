import action.OrderCreate;
import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.OrderModel;
import model.UserModel;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderNoIngredientTest extends BaseTestWithCreateUser {
    OrderModel order;
    private UserModel user;
    Response response;
    OrderCreate orderCreate;
    UserLogin userLogin;

    @DisplayName("Order with missing ingredients")
    @Description("Проверка с отсутствующим ингредиентом")
    @Test
    public void orderNoIngredientTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        userLogin = new UserLogin();
        userLogin.loginUserApi(user);
        order = new OrderModel();
        orderCreate = new OrderCreate();

        response = orderCreate.createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));  // Поле success = true// Поле success = true;
    }
}
