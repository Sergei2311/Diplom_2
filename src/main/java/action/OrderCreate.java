package action;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static action.DataAPI.ORDER_CREATE;
import static io.restassured.RestAssured.given;

public interface OrderCreate {

    @Step("Создание заказа")
    public default Response createOrder(List<String> ingredients) {
        Map<String, List<String>> requestBody = new HashMap<>();
        requestBody.put("ingredients", ingredients);
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(ORDER_CREATE);
    }
}
