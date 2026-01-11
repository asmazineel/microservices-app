package org.ecom.billingservice.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ecom.billingservice.model.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private Long CustomerId;
   @OneToMany(mappedBy = "bill")
    private List<ProductItem> productitems=  new ArrayList<ProductItem>();
   @Transient
    private Customer customer;
}
