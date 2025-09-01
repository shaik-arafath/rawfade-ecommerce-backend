package com.rawfade.ecom.dto;

import jakarta.validation.constraints.*;

public class AuthDtos {
    public static class SignupRequest {
        @Email @NotBlank public String email;
        @NotBlank @Size(min=6, max=100) public String password;
        @NotBlank @Size(min=2, max=60) public String fullName;
    }

    public static class LoginRequest {
        @Email @NotBlank public String email;
        @NotBlank public String password;
    }

    public static class JwtResponse {
        public String token;
        public String email;
        public String role;
        public JwtResponse(String token, String email, String role){ this.token=token; this.email=email; this.role=role; }
    }
}
