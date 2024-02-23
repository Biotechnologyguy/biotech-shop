package com.app.store.controller;

import com.app.store.dto.AuthRequest;
import com.app.store.dto.AuthResp;
import com.app.store.repository.IUserRepository;
import com.app.store.service.CustomUserDetailsService;
import com.app.store.utils.jwt.JwtUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.http.ResponseEntity.ok;
@RestController
@RequestMapping("/")
@Slf4j
public class LoginController {

    @Autowired
    private JwtUtils utils;
    // dep : Auth mgr
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping
    public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request) {
        // store incoming user details(not yet validated) into Authentication object
        // Authentication i/f ---> implemented by UserNamePasswordAuthToken
        String email = customUserDetailsService.isValidEmail(request.getEmail()) ? request.getEmail() : userRepo.findByUserName(request.getEmail()).get().getEmail();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email,
                request.getPassword());
        log.debug("auth token " + authToken);
        try {
            // authenticate the credentials
            Authentication authenticatedDetails = manager.authenticate(authToken);
            log.info("auth token again " + authenticatedDetails);
            // => auth succcess
            return ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails),authenticatedDetails));
        } catch (BadCredentialsException e) {
            // send back err resp code
            System.out.println("err " + e);
            return status(UNAUTHORIZED).body(e.getMessage());
        }

    }
}
