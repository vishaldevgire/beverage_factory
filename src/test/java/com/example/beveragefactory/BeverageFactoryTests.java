package com.example.beveragefactory;


import com.example.beveragefactory.model.OrderResult;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


import java.util.Collections;
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
    void shouldProcessOrderWithoutExclusions() {
        String[] orders = new String[] {"Coffee"};
        OrderResult expected = OrderResult.success(orders[0], 5d);
        List<OrderResult> actual = beverageFactory.processOrders(orders);

        assertThat(actual.get(0)).isEqualTo(expected);
    }

    @Test
    void shouldProcessOrderWithExclusions() {
        String[] orders = new String[] {"Chai, -sugar, -milk" };
        OrderResult expected = OrderResult.success(orders[0], 2.5);
        List<OrderResult> actual = beverageFactory.processOrders(orders);

        assertThat(actual.get(0)).isEqualTo(expected);
    }
}
