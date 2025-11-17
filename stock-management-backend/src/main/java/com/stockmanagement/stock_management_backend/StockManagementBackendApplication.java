package com.stockmanagement.stock_management_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagementBackendApplication.class, args);
        openSwaggerUI();
    }

    private static void openSwaggerUI() {
        try {
            String url = "http://localhost:8080/swagger-ui/index.html";
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", url});
        } catch (Exception e) {
            System.out.println("Couldn't open Swagger UI automatically: " + e.getMessage());
        }
    }

}
