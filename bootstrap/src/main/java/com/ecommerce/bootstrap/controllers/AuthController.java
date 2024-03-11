package com.ecommerce.bootstrap.controllers;

import com.ecommerce.bootstrap.services.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final TokenService _tokenService;

    public AuthController(TokenService tokenService) {

        _tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) throws AuthenticationException {
        return _tokenService.generateToken(authentication);
    }
}
