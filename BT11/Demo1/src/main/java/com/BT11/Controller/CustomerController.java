package com.BT11.Controller;

import com.BT11.DTO.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableWebSecurity
public class CustomerController {
    final private List<Customer> customers=List.of(Customer.builder().id("001").name("Trần Hồng Quang Lê").email("23110251@student.hcmute.edu.vn").build(),Customer.builder().id("002").name("Quang Lê").email("23110251@student.hcmute.edu.vn").build());
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }
    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Customer>> getCustomerList() {
        return ResponseEntity.ok(this.customers);
    }
    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                this.customers.stream()
                        .filter(customer -> customer.getId().equals(id))
                        .findFirst()
                        .orElse(null)
        );
    }
}
