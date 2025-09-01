package com.rawfade.ecom.controller;

import com.rawfade.ecom.model.Product;
import com.rawfade.ecom.repo.ProductRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepo;
    public ProductController(ProductRepository productRepo){ this.productRepo=productRepo; }

    @GetMapping
    public List<Product> list(){ return productRepo.findAll(); }

    @GetMapping("/{slug}")
    public Product getBySlug(@PathVariable String slug){ return productRepo.findBySlug(slug).orElseThrow(); }
}
