package com.chetan.my_chef_now.config;

import com.chetan.my_chef_now.repository.AdminRepo;
import com.chetan.my_chef_now.repository.ChefRepo;
import com.chetan.my_chef_now.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AdminRepo adminRepo;
    private final ChefRepo chefRepo;
    private final CustomerRepo customerRepo;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Check in all repositories
            var admin = adminRepo.findByAdminEmail(username);
            if (admin != null) return admin;

            var chef = chefRepo.findByChefEmail(username);
            if (chef != null) return chef;

            var customer = customerRepo.findByCustomerEmail(username);
            if (customer != null) return customer;

            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
