package com.ecommerce.ecommerce_backend.dto;

import com.ecommerce.ecommerce_backend.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
