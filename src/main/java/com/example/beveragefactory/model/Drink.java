package com.example.beveragefactory.model;

import java.util.ArrayList;
import java.util.List;

public class Drink {
    public Drink(List<Ingredient> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }

    public List<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    List<Ingredient> ingredients;
}
