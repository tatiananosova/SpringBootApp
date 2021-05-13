package com.example.springbootapp.repository;

import com.example.springbootapp.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final static List<Product> database = new CopyOnWriteArrayList<>();
    private final static AtomicInteger id = new AtomicInteger();

    static {
        database.add(new Product(id.incrementAndGet(), "Potato", 4, 1, false));
        database.add(new Product(id.incrementAndGet(), "Tomato", 5, 1, false));
        database.add(new Product(id.incrementAndGet(), "Sweet Potato", 6, 1, false));
        database.add(new Product(id.incrementAndGet(), "Cucumber", 7, 1, false));
        database.add(new Product(id.incrementAndGet(), "Radish", 8, 1, false));
        database.add(new Product(id.incrementAndGet(), "Paper", 9, 1, false));
        database.add(new Product(id.incrementAndGet(), "Paper Bell", 10, 1, false));
        database.add(new Product(id.incrementAndGet(), "Horse Radish", 1, 1, false));
        database.add(new Product(id.incrementAndGet(), "Zucchini", 2, 1, false));
        database.add(new Product(id.incrementAndGet(), "Pumpkin", 3, 1, false));
    }
    public void add(Product product) {
        product.setId(id.incrementAndGet());
        database.add(product);
    }

    public Product getProduct(int id) {
        return database.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public List<Product> findAll() {
        return database.stream().filter(product -> !product.isDeleted()).collect(Collectors.toUnmodifiableList());
    }

    public Product update(Product product) {
        if (product.getId() == null) {
            return null;
        }
        Product existingProduct = database.stream()
                .filter(it -> it.getId().equals(product.getId()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        product.setId(existingProduct.getId());
        product.setVersion(existingProduct.getVersion() + 1);
        database.add(product);
        return product;
    }

    public int deleteById(Integer id) {
        List<Product> productsToDelete = database.stream().filter(product -> product.getId().equals(id)).collect(Collectors.toList());
        productsToDelete.forEach(product -> product.setDeleted(true));
        return productsToDelete.size();
    }
}
