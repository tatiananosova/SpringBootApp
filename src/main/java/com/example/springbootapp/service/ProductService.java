package com.example.springbootapp.service;

import com.example.springbootapp.dto.CustomerDto;
import com.example.springbootapp.dto.ProductDto;
import com.example.springbootapp.model.Product;
import com.example.springbootapp.repository.CustomerRepository;
import com.example.springbootapp.repository.ProductRepository;
import com.example.springbootapp.util.CustomerConverter;
import com.example.springbootapp.util.ProductConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    public void save(ProductDto product) {
        if (product.getId() == null) {
            log.debug("Adding product");
            productRepository.add(ProductConverter.dtoToProduct(product));
        } else {
            log.debug("Updating product");
            productRepository.update(ProductConverter.dtoToProduct(product));
        }
    }

    public List<ProductDto> findAll() {
        log.debug("All products");
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        log.debug(products.toString());
        for (Product p : products) {
            productDtos.add(ProductConverter.productToDto(p));

        }
        return productDtos;
    }
    public ProductDto getProduct(int id) {
        log.debug("Get product");
        return ProductConverter.productToDto(productRepository.getProduct(id));
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> getCustomerProducts(int custId){
        return customerRepository.getProductList(custId).stream().map(ProductConverter::productToDto).collect(Collectors.toList());
    }
    public List<CustomerDto> getProductCustomers(int prodId){
        return productRepository.getCustomerList(prodId).stream().map(CustomerConverter::customerToDto).collect(Collectors.toList());
    }
}
