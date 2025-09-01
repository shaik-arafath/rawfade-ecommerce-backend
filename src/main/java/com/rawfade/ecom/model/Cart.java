package com.rawfade.ecom.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="carts", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy="cart", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    public Long getId(){ return id; }
    public User getUser(){ return user; }
    public void setUser(User u){ this.user=u; }
    public List<CartItem> getItems(){ return items; }
}
