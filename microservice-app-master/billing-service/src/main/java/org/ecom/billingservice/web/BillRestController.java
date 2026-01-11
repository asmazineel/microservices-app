package org.ecom.billingservice.web;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.ecom.billingservice.entities.Bill;
import org.ecom.billingservice.feign.CustomerRestClient;
import org.ecom.billingservice.feign.ProductRestClient;
import org.ecom.billingservice.repositories.BillRepository;
import org.ecom.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BillRestController {
    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private  final ProductRestClient productRestClient;


    @GetMapping("/bills/{id}")
    public Bill getBill( @PathVariable Long id) {
  Bill bill=billRepository.findById(id).orElseThrow();
  bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
  bill.getProductitems().forEach(item->{
      item.setProduct(productRestClient.getProductById(item.getProductId()));
  });
  return bill;
    }
}
