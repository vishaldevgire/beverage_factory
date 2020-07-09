package com.example.beveragefactory;

import com.example.beveragefactory.model.Drink;
import com.example.beveragefactory.model.Ingredient;
import com.example.beveragefactory.model.OrderResult;
import org.apache.commons.lang3.tuple.Pair;

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
        ingredients.add(new Ingredient("tea", 2));
        ingredients.add(new Ingredient("banana", 4));
        ingredients.add(new Ingredient("strawberries", 5));
        ingredients.add(new Ingredient("lemon", 5.5));


        drinks.put("coffee", toDrink("coffee, milk, sugar, water"));
        drinks.put("chai", toDrink("Tea, milk, sugar, water"));
        drinks.put("banana smoothie", toDrink("banana, milk, sugar, water"));
        drinks.put("strawberry shake", toDrink("strawberries, sugar, milk, water"));
        drinks.put("mojito", toDrink("Lemon, sugar, water, soda, mint"));
    }

    public BeverageFactory() {
        initialize();
    }

    private Drink toDrink(String ingredientNamesString) {
        List<String> ingredientNames = toList(ingredientNamesString);
        return new Drink(getIngredients(ingredientNames));
    }

    private List<String> toList(String commaSeperateStrings) {
        return Arrays.stream(commaSeperateStrings.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private List<Ingredient> getIngredients(List<String> ingredientNames) {
        return ingredientNames
                .stream()
                .map(ingredientName ->
                        ingredients
                                .stream()
                                .filter(ingredient ->
                                        ingredient.getName().equalsIgnoreCase(ingredientName)
                                )
                                .findFirst()
                                .get()).collect(Collectors.toList()
                );
    }

    public List<OrderResult> processOrders(String[] orders) {
        if (orders == null || orders.length == 0) {
            return new ArrayList<>();
        }

        List<OrderResult> result = Arrays.stream(orders).map(
                order -> prepareOrder(order)
        ).collect(Collectors.toList());

        return result;
    }

    private OrderResult prepareOrder(String orderCommaSeperated) {
        List<String> orderTokens = toList(orderCommaSeperated).stream().map(token -> {
            if (token.trim().startsWith("-")) {
                return token.trim().substring(1);
            }
            return token.trim();
        }).map(String::toLowerCase).collect(Collectors.toList());

        Drink drink = drinks.get(orderTokens.get(0).toLowerCase());

        if (drink == null ) {
            return OrderResult.orderMustContainAtleastOneMenuItem(orderCommaSeperated);
        }

        List<String> ingredientList = drink.getIngredients().stream().map(Ingredient::getName).collect(Collectors.toList());
        if (!ingredientList.containsAll(orderTokens.subList(1, orderTokens.size()))) {
            return OrderResult.orderMustNotContainInvalidExclusions(orderCommaSeperated);
        }

        List<Ingredient> ingredients = drink.getIngredients();
        List<Ingredient> exclusions = getIngredients(orderTokens.subList(1, orderTokens.size()));

        ingredients.removeAll(exclusions);

        if (ingredients.size() == 0) {
            return OrderResult.orderCanNotExcludeAllIngredients(orderCommaSeperated);
        }

        double price = ingredients.stream().mapToDouble(Ingredient::getPrice).sum();
        return OrderResult.success(orderCommaSeperated, price);
    }
}
