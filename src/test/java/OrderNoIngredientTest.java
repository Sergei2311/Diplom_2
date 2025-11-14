import action.OrderCreate;
import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.OrderModel;
import model.UserModel;
import org.junit.Test;

import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

public class OrderNoIngredientTest extends BaseTestWithCreateUser implements UserLogin, OrderCreate {
    OrderModel order;
    private UserModel user;
    Response response;

    @DisplayName("Order with missing ingredients")
    @Step("Проверка с отсутствующим ингредиентом")
    @Test
    public void orderNoIngredientTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        loginUserApi(user);
        order = new OrderModel();

        response = createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST);
    }
}
