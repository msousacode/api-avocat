package com.avocat.service;

import com.avocat.controller.customer.dto.CustomerDto;
import com.avocat.persistence.entity.Customer;
import com.avocat.persistence.entity.UserApp;
import com.avocat.persistence.repository.CustomerRepository;
import com.avocat.persistence.repository.PrivilegeRepository;
import com.avocat.persistence.repository.UserAppRepository;
import com.avocat.persistence.types.PrivilegesTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerService {

    @Autowired
    private UserAppRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Transactional
    public CustomerDto signupCustomer(Customer customer) {

        var privilege = privilegeRepository.findByName(PrivilegesTypes.ROLE_ADMIN.name());

        var userCreated = userRepository.save(
                new UserApp.Builder(customer.getEmail(), new BCryptPasswordEncoder()
                        .encode("12345678")).privilege(privilege).build());

        customer.setUser(userCreated);
        var result = customerRepository.save(customer);

        return CustomerDto.from(result);
    }
}