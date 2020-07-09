package com.example.beveragefactory;

import com.example.beveragefactory.model.Drink;
import com.example.beveragefactory.model.Ingredient;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeverageFactory {
    private List<Ingredient> ingredients = new ArrayList<>();
    private Map<String, Drink> drinks = new HashMap<>();

    private void initialize() {
        ingredients.add(new Ingredient("milk" , 1));
        ingredients.add(new Ingredient("sugar", 0.5));
        ingredients.add(new Ingredient("soda" , 0.5));
        ingredients.add(new Ingredient("mint" , 0.5));
        ingredients.add(new Ingredient("water", 0.5));

        ingredients.add(new Ingredient("coffee", 3));
        ingredients.add(new Ingredient("chai", 2));
        ingredients.add(new Ingredient("banana", 4));
        ingredients.add(new Ingredient("strawberries", 5));
        ingredients.add(new Ingredient("lemon", 5.5));

        drinks.put("coffee", new Drink(getIngredients("coffee, milk, sugar, water")));
    }

    private List<Ingredient> getIngredients(String ingredientNames) {
        return Arrays.stream(ingredientNames.split(","))
                .map(String::trim)
                .map(ingredientName ->
                    ingredients
                        .stream()
                        .filter(ingredient -> ingredient.getName().equals(ingredientName))
                        .findFirst()
                        .get()).collect(Collectors.toList()
                );
    }

    public List<Pair<String, Double>> processOrders(String[] orders) {
        return new ArrayList<>();
    }
}
