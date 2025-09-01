package com.rawfade.ecom.model;

import jakarta.persistence.*;

@Entity
@Table(name="cart_items")
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private int quantity = 1;

    public Long getId(){ return id; }
    public Cart getCart(){ return cart; }
    public void setCart(Cart c){ this.cart=c; }
    public Product getProduct(){ return product; }
    public void setProduct(Product p){ this.product=p; }
    public int getQuantity(){ return quantity; }
    public void setQuantity(int q){ this.quantity=q; }
}
