package org.ecom.customerservice;

import org.ecom.customerservice.config.CustomerConfigParams;
import org.ecom.customerservice.entities.Customer;
import org.ecom.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerrepository) {
        return args -> {
            customerrepository.save(Customer.builder()
                            .name("Mohamed")
                            .email("med@gmail.com")
                            .build());
            customerrepository.save(Customer.builder()
                    .name("Salma")
                    .email("salma@gmail.com")
                    .build());
            customerrepository.save(Customer.builder()
                    .name("saad")
                    .email("saad@gmail.com")
                    .build());

            customerrepository.findAll().forEach(c->{
                System.out.println("=================");
            System.out.println(c.getId());
            System.out.println(c.getName());
            System.out.println(c.getEmail());
            System.out.println("=================");
            }

            );
        };
    }

}
