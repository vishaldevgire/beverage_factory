package com.example.beveragefactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Map;

public class BeverageFactoryApplication {
    public static void main(String[] args) {
        String[] orders = {
                "Coffee",
                "Chai, -sugar, -milk"
        };

        new BeverageFactory().processOrders(orders);
    }
}
