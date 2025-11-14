import action.OrderCreate;
import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.OrderModel;
import model.UserModel;
import org.junit.Test;

import static action.DataAPI.INGREDIENT_ONE;
import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_SERVER_ERROR;

public class OrderIncorrectHash extends BaseTestWithCreateUser implements UserLogin, OrderCreate {
    OrderModel order;
    private UserModel user;
    Response response;

    @DisplayName("Order incorrect hash")
    @Step("Проверка с неверным хешем ингредиентов")
    @Test
    public void orderCreateNoLoginPositiveTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        loginUserApi(user);
        order = new OrderModel();
        order.addIngredient(INGREDIENT_ONE);
        order.addIngredient("qw123tyy");

        response = createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_SERVER_ERROR);  // Проверяем статус‑код
    }
}
