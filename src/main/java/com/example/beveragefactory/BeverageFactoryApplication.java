package com.example.beveragefactory;

import com.example.beveragefactory.model.OrderResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BeverageFactoryApplication {
    public static void main(String[] args) {
        String[] orders = {
                "Coffee",
                "Chai, -sugar, -milk",
                "Chai, -Ghee, -milk, -sugar, -water",
                "Random",
                "Chai, -Tea, -milk, -sugar, -water"
        };

        List<OrderResult> orderResults = new BeverageFactory().processOrders(orders);

        orderResults.forEach(orderResult -> System.out.println(orderResult));
    }
}
