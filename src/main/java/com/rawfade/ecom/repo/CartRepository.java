package com.rawfade.ecom.repo;

import com.rawfade.ecom.model.Cart;
import com.rawfade.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
