/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * NOTE: Recommended option from Homework description suggested 
 * out-of-date method with WebSecurityConfigurerAdapter
 * 
 * Configuration class for Spring Security settings.
 * Enables web security and defines beans for password encoding, user details service,
 * and the security filter chain.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Creates a PasswordEncoder bean that uses a delegating password encoder.
     * This allows for multiple password encoding strategies.
     *
     * @return A PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * Creates an in-memory UserDetailsService bean.
     * Defines two users: 'user' with the USER role and 'testuser01' with USER and ADMIN roles.
     * Passwords are encoded using the provided PasswordEncoder.
     *
     * @param passwordEncoder The PasswordEncoder bean to use for encoding passwords.
     * @return An InMemoryUserDetailsManager instance populated with predefined users.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("testuser01")
                .password(passwordEncoder.encode("password01"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    /**
     * Configures the security filter chain.
     * Requires authentication for all requests.
     * Configures form-based login with a custom login page ("/login") accessible to everyone.
     * Restricts access to monthly books editing to ADMIN role
     * Configures logout functionality, redirecting to "/login?logout=true" upon successful logout,
     * invalidating the HTTP session, and allowing public access to the logout process.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(c -> c
                        .requestMatchers(new AntPathRequestMatcher("/monthly-books/list"),
                                new AntPathRequestMatcher("/monthly-books/new"),
                                new AntPathRequestMatcher("/monthly-books")
                        ).hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(c -> c.loginPage("/login").permitAll())
                .logout(c -> c.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .build();
    }
}
