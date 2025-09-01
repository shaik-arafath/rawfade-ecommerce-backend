package com.rawfade.ecom.controller;

import com.rawfade.ecom.model.*;
import com.rawfade.ecom.repo.*;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final BannerRepository bannerRepo;
    private final SiteTextRepository textRepo;

    public AdminController(ProductRepository productRepo, CategoryRepository categoryRepo,
                           BannerRepository bannerRepo, SiteTextRepository textRepo){
        this.productRepo=productRepo; this.categoryRepo=categoryRepo; this.bannerRepo=bannerRepo; this.textRepo=textRepo;
    }

    // Products
    @GetMapping("/products") public List<Product> allProducts(){ return productRepo.findAll(); }
    @PostMapping("/products") public Product createProduct(@Valid @RequestBody Product p){ return productRepo.save(p); }
    @PutMapping("/products/{id}") public Product updateProduct(@PathVariable Long id, @RequestBody Product p){ p.setId(id); return productRepo.save(p); }
    @DeleteMapping("/products/{id}") public void deleteProduct(@PathVariable Long id){ productRepo.deleteById(id); }

    // Categories
    @GetMapping("/categories") public List<Category> allCats(){ return categoryRepo.findAll(); }
    @PostMapping("/categories") public Category createCat(@RequestBody Category c){ return categoryRepo.save(c); }

    // Banners
    @GetMapping("/banners") public List<Banner> allBanners(){ return bannerRepo.findAll(); }
    @PostMapping("/banners") public Banner createBanner(@RequestBody Banner b){ return bannerRepo.save(b); }
    @PutMapping("/banners/{id}") public Banner updateBanner(@PathVariable Long id, @RequestBody Banner b){ b.setId(id); return bannerRepo.save(b); }
    @DeleteMapping("/banners/{id}") public void deleteBanner(@PathVariable Long id){ bannerRepo.deleteById(id); }

    // Site texts
    @GetMapping("/texts") public List<SiteText> allTexts(){ return textRepo.findAll(); }
    @PostMapping("/texts") public SiteText createText(@RequestBody SiteText t){ return textRepo.save(t); }
    @PutMapping("/texts/{id}") public SiteText updateText(@PathVariable Long id, @RequestBody SiteText t){ t.setId(id); return textRepo.save(t); }
    @DeleteMapping("/texts/{id}") public void deleteText(@PathVariable Long id){ textRepo.deleteById(id); }
}
