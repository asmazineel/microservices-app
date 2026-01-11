package org.ecom.billingservice.feign;

import org.ecom.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Customer-service")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    public Customer getCustomerById( @PathVariable Long id);

    //hateos Model parceque le resultat
    // des customers n'est pas une liste alors on declare pas une liste
    @GetMapping("/customers")
    PagedModel<Customer> getCustomers();
}
