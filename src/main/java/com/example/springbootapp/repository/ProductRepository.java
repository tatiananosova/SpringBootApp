package com.example.springbootapp.repository;

import com.example.springbootapp.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private static SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
    public void add(Product product) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public Product getProduct(int id) {
        Product product = null;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> product;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            product = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
        }
        return product;
    }

    public Product update(Product product) {
        if (product.getId() == null) {
            return null;
        }
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product1 = session.get(Product.class, product.getId());
            product1.setTitle(product.getTitle());
            product1.setCost(product.getCost());
            product1.setVersion(product1.getVersion() + 1);
            product1.setDeleted(product.isDeleted());
            session.getTransaction().commit();
        }
        return product;
    }

    public int deleteById(Integer id) {
        Product product = getProduct(id);
        product.setDeleted(true);
        return update(product).getId();
    }
}
