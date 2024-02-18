package com.app.store.controller;

import com.app.store.dto.AuthRequest;
import com.app.store.dto.AuthResp;
import com.app.store.utils.jwt.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class LoginController {

    @Autowired
    private JwtUtils utils;
    // dep : Auth mgr
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request) {
        // store incoming user details(not yet validated) into Authentication object
        // Authentication i/f ---> implemented by UserNamePasswordAuthToken
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword());
        log.info("auth token " + authToken);
        try {
            // authenticate the credentials
            Authentication authenticatedDetails = manager.authenticate(authToken);
            log.info("auth token again " + authenticatedDetails);
            // => auth succcess
            return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails),authenticatedDetails));
        } catch (BadCredentialsException e) {
            // send back err resp code
            System.out.println("err " + e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
