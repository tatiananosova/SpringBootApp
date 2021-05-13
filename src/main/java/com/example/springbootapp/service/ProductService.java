package com.example.springbootapp.service;

import com.example.springbootapp.dto.ProductDto;
import com.example.springbootapp.repository.ProductRepository;
import com.example.springbootapp.util.ProductConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService {
    private final ProductRepository productRepository;

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
        return productRepository.findAll().stream().map(ProductConverter::productToDto).collect(Collectors.toList());
    }
    public ProductDto getProduct(int id) {
        log.debug("Get product");
        return ProductConverter.productToDto(productRepository.getProduct(id));
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
