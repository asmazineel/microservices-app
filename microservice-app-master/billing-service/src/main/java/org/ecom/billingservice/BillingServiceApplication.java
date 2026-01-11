package org.ecom.billingservice;

import org.ecom.billingservice.entities.Bill;
import org.ecom.billingservice.entities.ProductItem;
import org.ecom.billingservice.feign.CustomerRestClient;
import org.ecom.billingservice.feign.ProductRestClient;
import org.ecom.billingservice.model.Customer;
import org.ecom.billingservice.model.Product;
import org.ecom.billingservice.repositories.BillRepository;
import org.ecom.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BillRepository  billRepository,
    ProductItemRepository productItemRepository,
    ProductRestClient   productRestClient,
    CustomerRestClient  customerRestClient) {
        return args -> {
            Collection<Customer> customers = customerRestClient.getCustomers().getContent();
            Collection<Product> products = productRestClient.getProducts().getContent();

            customers.forEach(customer -> {
                Bill bill = Bill.builder()
                        .billingDate(new Date())
                        .CustomerId(customer.getId())
                        .build();
                billRepository.save(bill);

                products.forEach(product -> {
                    ProductItem productItem = ProductItem.builder()
                            .bill(bill)
                            .productId(product.getId())
                            .quantity(new Random().nextInt(10))
                            .unitprice(product.getPrice())
                            .build();
                    productItemRepository.save(productItem);
                });
            });
        };
    }

}
