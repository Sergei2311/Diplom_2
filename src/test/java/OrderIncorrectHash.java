import action.OrderCreate;
import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.OrderModel;
import model.UserModel;
import org.junit.Test;

import static action.DataAPI.INGREDIENT_ONE;
import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_SERVER_ERROR;

public class OrderIncorrectHash extends BaseTestWithCreateUser {
    OrderModel order;
    private UserModel user;
    Response response;
    OrderCreate orderCreate;
    UserLogin userLogin;

    @DisplayName("Order incorrect hash")
    @Description("Проверка с неверным хешем ингредиентов")
    @Test
    public void orderCreateNoLoginPositiveTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        userLogin = new UserLogin();
        userLogin.loginUserApi(user);
        order = new OrderModel();
        order.addIngredient(INGREDIENT_ONE);
        order.addIngredient("qw123tyy");
        orderCreate = new OrderCreate();


        response = orderCreate.createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_SERVER_ERROR);  // Проверяем статус‑код
    }
}
