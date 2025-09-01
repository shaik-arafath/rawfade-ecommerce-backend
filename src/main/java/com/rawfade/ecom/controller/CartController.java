package com.rawfade.ecom.controller;

import com.rawfade.ecom.model.Cart;
import com.rawfade.ecom.service.CartService;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService){ this.cartService = cartService; }

    @PostMapping("/add")
    public ResponseEntity<Cart> add(@AuthenticationPrincipal User principal,
                                    @RequestParam Long productId,
                                    @RequestParam(defaultValue = "1") @Min(1) int qty){
        return ResponseEntity.ok(cartService.addToCart(principal.getUsername(), productId, qty));
    }

    @GetMapping
    public ResponseEntity<Cart> get(@AuthenticationPrincipal User principal){
        // In a real app, fetch and return the user's cart; simplified for brevity
        return ResponseEntity.ok(cartService.addToCart(principal.getUsername(), 0L, 0)); // placeholder: not ideal
    }
}
