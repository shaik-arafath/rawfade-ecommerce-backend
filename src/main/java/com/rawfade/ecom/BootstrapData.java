package com.rawfade.ecom;

import com.rawfade.ecom.model.*;
import com.rawfade.ecom.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class BootstrapData {
    @Bean
    CommandLineRunner init(RoleRepository roleRepo, UserRepository userRepo,
                           CategoryRepository catRepo, ProductRepository prodRepo,
                           BannerRepository bannerRepo, SiteTextRepository textRepo,
                           PasswordEncoder encoder){
        return args -> {
            Role adminRole = roleRepo.findByName("ROLE_ADMIN").orElseGet(() -> roleRepo.save(new Role("ROLE_ADMIN")));
            Role userRole  = roleRepo.findByName("ROLE_USER").orElseGet(() -> roleRepo.save(new Role("ROLE_USER")));

            if (userRepo.findByEmail("admin@rawfadeclothing.com").isEmpty()){
                User admin = new User();
                admin.setEmail("admin@rawfadeclothing.com");
                admin.setFullName("Admin");
                admin.setPassword(encoder.encode("Admin@123"));
                admin.getRoles().add(adminRole);
                userRepo.save(admin);
            }

            Category tees = catRepo.findBySlug("t-shirts").orElseGet(() -> {
                Category c = new Category();
                c.setName("T-Shirts"); c.setSlug("t-shirts");
                return catRepo.save(c);
            });

            if (prodRepo.findAll().isEmpty()){
                Product p = new Product();
                p.setTitle("Raw Fade Classic Tee");
                p.setSlug("raw-fade-classic-tee");
                p.setDescription("Premium cotton tee with Raw Fade branding.");
                p.setCategory(tees);
                p.setPrice(new BigDecimal("799.00"));
                p.setThumbnailUrl("https://rawfadeclothing.com/img/products/classic-tee.jpg");
                prodRepo.save(p);
            }

            if (bannerRepo.findAll().isEmpty()){
                Banner b = new Banner();
                b.setTitle("End of Season Sale");
                b.setSubtitle("Up to 40% off");
                b.setImageUrl("https://rawfadeclothing.com/img/banners/eos-sale.jpg");
                b.setCtaText("Shop Now");
                b.setCtaLink("/shop");
                bannerRepo.save(b);
            }

            textRepo.findByKeyName("HOME_HERO_TITLE").orElseGet(() -> {
                SiteText t = new SiteText();
                t.setKeyName("HOME_HERO_TITLE");
                t.setValue("Streetwear that lasts.");
                return textRepo.save(t);
            });
        };
    }
}
