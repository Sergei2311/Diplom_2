package data;

import action.CreateUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import model.UserModel;
import org.junit.After;
import org.junit.Before;

import static action.DataAPI.BASEURL;
import static action.DataAPI.USER_DELETE;
import static data.TestData.*;
import static io.restassured.RestAssured.given;

public class BaseTestWithCreateUser {
    private String userID;
    private UserModel user;
    CreateUser createUser;

    // BaseTest с созданием пользователя
    @Before
    public void setup() {
        RestAssured.baseURI = BASEURL;
        user = new UserModel(EMAIL, PASSWORD, NAME);
        createUser = new CreateUser();
        createUser.createUserApi(user);
    }

    @After  // удаляем пользователя
    public void userDelete() {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .delete(USER_DELETE);
    }
}
