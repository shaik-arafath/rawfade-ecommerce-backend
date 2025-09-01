package com.rawfade.ecom.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name="products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=180)
    private String title;

    @Column(nullable=false, unique=true, length=200)
    private String slug;

    @Column(length=2000)
    private String description;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Column(nullable=false, precision=12, scale=2)
    private BigDecimal price;

    private String thumbnailUrl;
    private boolean active = true;

    private Instant createdAt = Instant.now();

    public Long getId(){ return id; }
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }
    public String getSlug(){ return slug; }
    public void setSlug(String slug){ this.slug = slug; }
    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description = description; }
    public Category getCategory(){ return category; }
    public void setCategory(Category c){ this.category = c; }
    public java.math.BigDecimal getPrice(){ return price; }
    public void setPrice(java.math.BigDecimal price){ this.price = price; }
    public String getThumbnailUrl(){ return thumbnailUrl; }
    public void setThumbnailUrl(String url){ this.thumbnailUrl = url; }
    public boolean isActive(){ return active; }
    public void setActive(boolean a){ this.active = a; }
    public Instant getCreatedAt(){ return createdAt; }
    public void setCreatedAt(Instant t){ this.createdAt = t; }
}

    public void setId(Long id){ this.id = id; }
