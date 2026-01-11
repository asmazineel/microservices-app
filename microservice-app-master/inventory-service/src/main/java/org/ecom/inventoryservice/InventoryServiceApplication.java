package org.ecom.inventoryservice;

import org.ecom.inventoryservice.entities.Product;
import org.ecom.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Product 1")
                    .price(1299)
                    .quantity(2)
                    .selected(true)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Product 2")
                    .price(1299)
                    .quantity(4)
                    .selected(true)
                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Product 3")
                    .price(1299)
                    .quantity(3)
                    .selected(true)
                    .build());

            productRepository.findAll().forEach(p->{
                        System.out.println("=================");
                        System.out.println(p.getId());
                        System.out.println(p.getName());
                        System.out.println(p.getPrice());
                        System.out.println(p.getQuantity());
                        System.out.println("=================");
                    }

            );
        };
    }
}
