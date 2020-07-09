package com.example.beveragefactory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeverageFactoryTests {
    BeverageFactory beverageFactory = new BeverageFactory();

    @Test
    void shouldReturnEmptyResultWhenOrdersIsEmptyOrNull() {
        assertThat(beverageFactory.processOrders(new String[0])).isEmpty();
        assertThat(beverageFactory.processOrders(null)).isEmpty();
    }
}
