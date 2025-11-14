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
import static action.DataAPI.INGREDIENT_TWO;
import static data.TestData.*;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderWithLoginAndIngredientTest extends BaseTestWithCreateUser implements UserLogin, OrderCreate {
    OrderModel order;
    private UserModel user;
    Response response;

    @DisplayName("Creating an order with authorization")
    @Step("Создание заказа с ингредиентами с авторизацией")
    @Test
    public void orderCreateNoLoginPositiveTest() {
        user = new UserModel(EMAIL, PASSWORD, NAME);
        loginUserApi(user);
        order = new OrderModel();
        order.addIngredient(INGREDIENT_ONE);
        order.addIngredient(INGREDIENT_TWO);

        response = createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_OK)  // Проверяем статус‑код
                .body("success", equalTo(true))  // Поле success = true
                .body("order.number", notNullValue());  // Есть номер заказа
    }
}
