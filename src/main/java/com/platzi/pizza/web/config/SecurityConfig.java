package com.platzi.pizza.web.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityProperties(HttpSecurity httpSecurity) throws Exception {
        //httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
        //httpSecurity.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
        //* <-- primer nivel ; ** <-- segundo nivel
        httpSecurity
                .csrf()
                .disable()
                .cors().and().authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","CUSTOMER")
                .requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT).denyAll()
                .anyRequest().authenticated().and().httpBasic();
        return httpSecurity.build();
    }
    /*
    @Bean
    public UserDetailsService getUserMemoryDetailsService(){
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(admin);
    }*/

    @Bean
    public AuthenticationManager authenticate(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

