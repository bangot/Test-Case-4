package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "order_session")
public class Order_Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id",nullable = false)
    private Product product;

    @OneToOne(cascade = {CascadeType.REMOVE }, fetch =FetchType.EAGER)
    @JoinColumn(name = "order_session",referencedColumnName = "id",nullable = false)
    private Order_confirm orderConfirm;


    public Order_Session(Product product) {
        this.product = product;
    }

    public Order_Session() {
    }

    public Order_Session(Long id) {
        this.id = id;
    }

    public Order_Session(Long id, Product product, Order_confirm orderConfirm) {
        this.id = id;
        this.product = product;
        this.orderConfirm = orderConfirm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order_confirm getOrderConfirm() {
        return orderConfirm;
    }

    public void setOrderConfirm(Order_confirm orderConfirm) {
        this.orderConfirm = orderConfirm;
    }
}
