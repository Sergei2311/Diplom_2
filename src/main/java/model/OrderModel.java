package model;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {
    private List<String> ingredients;

    public OrderModel() {
        this.ingredients = new ArrayList<>(); // Инициализация списка
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
