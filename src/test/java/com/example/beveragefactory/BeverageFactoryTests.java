package com.example.beveragefactory;


import com.example.beveragefactory.model.OrderResult;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BeverageFactoryTests {
    BeverageFactory beverageFactory = new BeverageFactory();

    @Test
    void shouldReturnEmptyResultWhenOrdersIsEmptyOrNull() {
        assertThat(beverageFactory.processOrders(new String[0])).isEmpty();
        assertThat(beverageFactory.processOrders(null)).isEmpty();
    }

    @Test
    void shouldProcessOrder_WithoutExclusions() {
        String[] orders = new String[] {"Coffee"};
        OrderResult expected = OrderResult.success(orders[0], 5d);
        List<OrderResult> actual = beverageFactory.processOrders(orders);

        assertThat(actual.get(0)).isEqualTo(expected);
    }

    @Test
    void shouldProcessOrder_WithExclusions() {
        String[] orders = new String[] {"Chai, -sugar, -milk" };
        OrderResult expected = OrderResult.success(orders[0], 2.5);
        List<OrderResult> actual = beverageFactory.processOrders(orders);

        assertThat(actual.get(0)).isEqualTo(expected);
    }

    @Test
    void shouldProcess_AllOrderCombinations() {
        String[] orders = new String[] {"Coffee", "Chai, -sugar, -milk" };
        List<OrderResult> results = beverageFactory.processOrders(orders);

        assertThat(results.get(0)).isEqualTo(OrderResult.success(orders[0], 5d));
        assertThat(results.get(1)).isEqualTo(OrderResult.success(orders[1], 2.5));
    }

    @Test
    void shouldReturnErrorMessageWhen_NotEventOneMenuItemIsSpecified() {
        String[] orders = new String[] {"Random"};
        List<OrderResult> results = beverageFactory.processOrders(orders);

        assertThat(results.get(0)).isEqualTo(
                OrderResult.orderMustContainAtleastOneMenuItem("Random")
        );
    }

    @Test
    void shouldReturnErrorMessageWhen_AllIngredientsOfMenuItemAreExcluded() {
        String[] orders = new String[] {"Chai, -Tea, -milk, -sugar, -water"};
        List<OrderResult> results = beverageFactory.processOrders(orders);

        assertThat(results.get(0)).isEqualTo(
                OrderResult.orderCanNotExcludeAllIngredients(orders[0])
        );
    }

    @Test
    void shouldReturnErrorMessageWhen_InvalidExclusionsAreSpecifiedInOrder() {
        String[] orders = new String[] {"Chai, -Ghee, -milk, -sugar, -water"};
        List<OrderResult> results = beverageFactory.processOrders(orders);

        assertThat(results.get(0)).isEqualTo(
                OrderResult.orderMustNotContainInvalidExclusions(orders[0])
        );
    }
}
