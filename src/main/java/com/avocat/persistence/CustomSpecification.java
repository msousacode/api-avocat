package com.avocat.persistence;

import com.avocat.persistence.entity.BranchOffice;
import com.avocat.persistence.entity.Customer;
import com.avocat.persistence.entity.UserApp;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;

public class CustomSpecification {

    public static Specification<Customer> byUsername(String username) {
        return (root, query, cb) -> {
            Join<Customer, UserApp> customer = root.join("user");
            customer.join("branchOffice");
            return cb.equal(customer.get("username"), username);
        };
    }
}
