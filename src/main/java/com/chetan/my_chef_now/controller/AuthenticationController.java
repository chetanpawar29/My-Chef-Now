package com.chetan.my_chef_now.controller;

import com.chetan.my_chef_now.model.AuthenticationRequest;
import com.chetan.my_chef_now.model.AuthenticationResponse;
import com.chetan.my_chef_now.model.Chef;
import com.chetan.my_chef_now.model.Customer;
import com.chetan.my_chef_now.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register/chef")
    public ResponseEntity<AuthenticationResponse> registerChef(@RequestBody Chef chef) {
        return ResponseEntity.ok(authenticationService.registerChef(chef));
    }

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(authenticationService.registerCustomer(customer));
    }

    @PostMapping("/authenticate/admin")
    public ResponseEntity<AuthenticationResponse> authenticateAdmin(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateAdmin(request));
    }

    @PostMapping("/authenticate/chef")
    public ResponseEntity<AuthenticationResponse> authenticateChef(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateChef(request));
    }

    @PostMapping("/authenticate/customer")
    public ResponseEntity<AuthenticationResponse> authenticateCustomer(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticateCustomer(request));
    }
}
