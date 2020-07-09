package com.example.beveragefactory;


import org.apache.commons.lang3.tuple.Pair;
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
        List<Pair<String, Double>> result = Collections.singletonList(
                Pair.of("Coffee", 5d)
        );

        assertThat(beverageFactory.processOrders(orders)).isEqualTo(result);
    }

    @Test
    void shouldProcessOrderWithExclusions() {
        String[] orders = new String[] {"Chai, -sugar, -milk" };
        List<Pair<String, Double>> result = Collections.singletonList(
                Pair.of("Chai, -sugar, -milk", 2.5)
        );

        assertThat(beverageFactory.processOrders(orders)).isEqualTo(result);
    }
}
