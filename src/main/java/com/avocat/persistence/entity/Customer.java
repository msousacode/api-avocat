package com.avocat.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private UUID id;

    @NotEmpty(message = "invalid format full name")
    @Column(name = "full_name")
    private String fullName;

    @NotEmpty(message = "invalid format office name")
    @Column(name = "office_name")
    private String officeName;

    @Column(name = "email")
    @Email(message = "invalid format email")
    private String email;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserApp user;

    private Customer(String fullName, String officeName, String email, UserApp user) {
        Assert.notNull(user, "invalid user. can't be null");
        this.fullName = fullName;
        this.officeName = officeName;
        this.email = email;
        this.user = user;
    }

    private Customer(String fullName, String officeName, String email) {
        this.fullName = fullName;
        this.officeName = officeName;
        this.email = email;
    }

    public static Customer getInstanceWithUser(Customer customer, UserApp user) {
        return new Customer(customer.getFullName(), customer.getOfficeName(), customer.getEmail(), user);
    }

    public static Customer getInstanceWithOutUser(String fullName, String officeName, String email) {
        return new Customer(fullName, officeName, email);
    }
}
