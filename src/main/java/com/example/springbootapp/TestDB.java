package com.example.springbootapp;

import com.example.springbootapp.model.Customer;
import com.example.springbootapp.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TestDB {
    private static SessionFactory factory;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
    public static void main(String[] args) {
        testManyToMany();
    }
    private static void testGetAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> product = session.createQuery("select p from Product p").getResultList();
            System.out.println(product.toString());
            session.getTransaction().commit();
        }
    }

    private static void testManyToMany() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> product = session.get(Customer.class, 1).getProductList();
            System.out.println(product.toString());
            session.getTransaction().commit();
        }
    }
}
