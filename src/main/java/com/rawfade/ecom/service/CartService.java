package com.rawfade.ecom.service;

import com.rawfade.ecom.model.*;
import com.rawfade.ecom.repo.CartRepository;
import com.rawfade.ecom.repo.ProductRepository;
import com.rawfade.ecom.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, UserRepository userRepo, ProductRepository productRepo){
        this.cartRepo = cartRepo; this.userRepo=userRepo; this.productRepo=productRepo;
    }

    @Transactional
    public Cart addToCart(String email, Long productId, int qty){
        User user = userRepo.findByEmail(email).orElseThrow();
        Cart cart = cartRepo.findByUser(user).orElseGet(() -> {
            Cart c = new Cart(); c.setUser(user); return cartRepo.save(c);
        });
        Product p = productRepo.findById(productId).orElseThrow();
        var item = cart.getItems().stream().filter(ci -> ci.getProduct().getId().equals(productId)).findFirst();
        if (item.isPresent()) {
            item.get().setQuantity(item.get().getQuantity()+qty);
        } else {
            CartItem ci = new CartItem();
            ci.setCart(cart);
            ci.setProduct(p);
            ci.setQuantity(qty);
            cart.getItems().add(ci);
        }
        return cartRepo.save(cart);
    }
}
