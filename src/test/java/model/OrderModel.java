package model;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private List<String> ingredients;

    public OrderModel() {
        this.ingredients = new ArrayList<>(); // Инициализация списка
    }
    @Step("Добавить ингредиент")
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }
    @Step("Получить ингредиент")
    public List<String> getIngredients() {
        return ingredients;
    }
    @Step("Создать ингредиент")
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
