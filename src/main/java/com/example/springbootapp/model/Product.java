package com.example.springbootapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "customerList")
@EqualsAndHashCode(exclude = "customerList")
@AllArgsConstructor()
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "version")
    private Integer version;

    @ManyToMany
    @JoinTable(
            name = "customer_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customerList;
}
