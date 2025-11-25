import action.OrderCreate;
import action.UserLogin;
import data.BaseTestWithCreateUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.OrderModel;
import org.junit.Test;

import static action.DataAPI.*;
import static java.net.HttpURLConnection.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

// Проверка с неверным хешем ингредиентов, Проверка с отсутствующим ингредиентом, Заказ с ингредиентами и авторизацией
// Cоздание заказа с ингредиентами и без авторизации
public class OrderCreateTest extends BaseTestWithCreateUser {
    OrderModel order;
    Response response;
    OrderCreate orderCreate;
    UserLogin userLogin;

    @DisplayName("Order incorrect hash")
    @Description("Проверка с неверным хешем ингредиентов")
    @Test
    public void orderCreateNoLoginPositiveTest() {
        order = new OrderModel();
        order.addIngredient(INGREDIENT_ONE);
        order.addIngredient("qw123tyy");
        orderCreate = new OrderCreate();


        response = orderCreate.createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_SERVER_ERROR);  // Проверяем статус‑код
    }

    @DisplayName("Order with missing ingredients")
    @Description("Проверка с отсутствующим ингредиентом")
    @Test
    public void orderNoIngredientTest() {
        order = new OrderModel();
        orderCreate = new OrderCreate();

        response = orderCreate.createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));  // Поле success = true// Поле success = true;
    }

    @DisplayName("Creating an order with authorization")
    @Description("Создание заказа с ингредиентами с авторизацией")
    @Test
    public void orderWithIngredientsAndLogin() {
        order = new OrderModel();
        order.addIngredient(INGREDIENT_ONE);
        order.addIngredient(INGREDIENT_TWO);
        orderCreate = new OrderCreate();

        response = orderCreate.createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_OK)  // Проверяем статус‑код
                .body("success", equalTo(true))  // Поле success = true
                .body("order.number", notNullValue());  // Есть номер заказа
    }

    @DisplayName("Creating an order with out authorization")
    @Description("Создание заказа с ингредиентами и без авторизации")
    @Test
    public void orderNoLoginWitnIngredientTest() {
        RestAssured.baseURI = BASEURL;
        order = new OrderModel();
        order.addIngredient(INGREDIENT_ONE);
        order.addIngredient(INGREDIENT_TWO);
        orderCreate = new OrderCreate();
        userLogin = new UserLogin();
        userLogin.logout();


        response = orderCreate.createOrder(order.getIngredients());
        response.then()
                .log().all()
                .statusCode(HTTP_OK)  // Проверяем статус‑код
                .body("success", equalTo(true))  // Поле success = true
                .body("order.number", notNullValue());  // Есть номер заказа


    }
}
