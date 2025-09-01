package com.rawfade.ecom.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="categories")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=120)
    private String name;

    @Column(nullable=false, unique=true, length=160)
    private String slug;

    @OneToMany(mappedBy="category", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public List<Product> getProducts(){ return products; }
}

    public void setId(Long id){ this.id = id; }
