import action.OrderCreate;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.OrderModel;
import org.junit.Test;

import static action.DataAPI.*;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderNoLoginWitnIngredientTest implements OrderCreate {
    OrderModel order;
    Response response;

    @DisplayName("Creating an order with out authorization")
    @Step("Создание заказа с ингредиентами и без авторизации")
    @Test
    public void orderCreateNoLoginPositiveTest() {
        RestAssured.baseURI = BASEURL;
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
