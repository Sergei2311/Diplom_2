package action;

import com.github.javafaker.Faker;

public class DataAPI {
    public static final String BASEURL = "https://stellarburgers.education-services.ru";
    public static final String USER_CREATE = "/api/auth/register";
    public static final String USER_DELETE = "/api/auth/user";
    public static final String USER_LOGIN = "/api/auth/login";
    public static final String ORDER_CREATE = "/api/orders";

    static Faker user = new Faker();
    public static final String EMAIL = user.regexify("[a-z]{8}")+"@test.ru";
    public static final String PASSWORD = user.regexify("[0-9]{4}");
    public static final String NAME = user.name().firstName();

      public static final String INGREDIENT_ONE = "61c0c5a71d1f82001bdaaa6c";
      public static final String INGREDIENT_TWO = "61c0c5a71d1f82001bdaaa6e";
}
