package data;

import com.github.javafaker.Faker;
import org.junit.Test;

public class TestData {
    static Faker user = new Faker();
    public static final String EMAIL = user.regexify("[a-z]{8}")+"@test.ru";
    public static final String PASSWORD = user.regexify("[0-9]{4}");
    public static final String NAME = user.name().firstName();

    }
