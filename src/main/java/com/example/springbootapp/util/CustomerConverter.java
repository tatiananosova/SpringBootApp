package com.example.springbootapp.util;

import com.example.springbootapp.dto.CustomerDto;
import com.example.springbootapp.dto.ProductDto;
import com.example.springbootapp.model.Customer;
import com.example.springbootapp.model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerConverter {

    public static Customer dtoToCustomer(CustomerDto customerDto) {
        return new Customer(customerDto.getId(), customerDto.getName(), new ArrayList<>());
    }

    public static CustomerDto customerToDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName());
    }
}
