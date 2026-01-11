package org.ecom.billingservice.feign;

import jakarta.ws.rs.Path;
import org.ecom.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface ProductRestClient {
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String  id);

    @GetMapping("/products")
     PagedModel<Product> getProducts();
}
