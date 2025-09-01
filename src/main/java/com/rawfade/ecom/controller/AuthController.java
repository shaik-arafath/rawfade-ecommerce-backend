package com.rawfade.ecom.controller;

import com.rawfade.ecom.dto.AuthDtos.*;
import com.rawfade.ecom.model.Role;
import com.rawfade.ecom.model.User;
import com.rawfade.ecom.repo.RoleRepository;
import com.rawfade.ecom.repo.UserRepository;
import com.rawfade.ecom.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder,
                          AuthenticationManager authManager, JwtService jwt){
        this.userRepo=userRepo; this.roleRepo=roleRepo; this.encoder=encoder; this.authManager=authManager; this.jwt=jwt;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest req){
        if (userRepo.existsByEmail(req.email)) return ResponseEntity.badRequest().body(Map.of("error","Email already used"));
        User u = new User();
        u.setEmail(req.email);
        u.setFullName(req.fullName);
        u.setPassword(encoder.encode(req.password));
        var role = roleRepo.findByName("ROLE_USER").orElseGet(() -> roleRepo.save(new Role("ROLE_USER")));
        u.getRoles().add(role);
        userRepo.save(u);
        String token = jwt.generateToken(u.getEmail(), Map.of("role","USER"));
        return ResponseEntity.ok(new JwtResponse(token, u.getEmail(), "USER"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req){
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.email, req.password));
        String role = auth.getAuthorities().stream().findFirst().map(a->a.getAuthority()).orElse("ROLE_USER");
        String token = jwt.generateToken(req.email, Map.of("role", role.replace("ROLE_","")));
        return ResponseEntity.ok(new JwtResponse(token, req.email, role.replace("ROLE_","")));
    }
}
