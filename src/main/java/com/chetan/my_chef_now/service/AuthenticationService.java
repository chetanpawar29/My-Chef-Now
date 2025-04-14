package com.chetan.my_chef_now.service;

import com.chetan.my_chef_now.model.*;
import com.chetan.my_chef_now.repository.AdminRepo;
import com.chetan.my_chef_now.repository.ChefRepo;
import com.chetan.my_chef_now.repository.CustomerRepo;
import com.chetan.my_chef_now.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ChefRepo chefRepo;
    private final CustomerRepo customerRepo;
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerChef(Chef chef) {
        chef.setChefPassword(passwordEncoder.encode(chef.getChefPassword()));
        chef.setChefStatus("Pending");
        chef.setRole(Role.CHEF);
        Chef savedChef = chefRepo.save(chef);
        var jwtToken = jwtService.generateToken(savedChef);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerCustomer(Customer customer) {
        customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
        customer.setRole(Role.CUSTOMER);
        Customer savedCustomer = customerRepo.save(customer);
        var jwtToken = jwtService.generateToken(savedCustomer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateAdmin(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var admin = adminRepo.findByAdminEmail(request.getEmail());
            if (admin == null) {
                throw new UsernameNotFoundException("Admin not found");
            }
            var jwtToken = jwtService.generateToken(admin);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    public AuthenticationResponse authenticateChef(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var chef = chefRepo.findByChefEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(chef);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateCustomer(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var customer = customerRepo.findByCustomerEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
