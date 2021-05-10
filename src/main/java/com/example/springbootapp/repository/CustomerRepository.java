package com.example.springbootapp.repository;

import com.example.springbootapp.model.Customer;
import com.example.springbootapp.model.Product;
import com.example.springbootapp.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public List<Product> getProductList(int custId) {
        List<Product> products;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            products = new ArrayList<>(session.get(Customer.class, custId).getProductList());
            session.getTransaction().commit();
        }
        return products;
    }
}
