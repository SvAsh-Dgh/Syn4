package com.syn.bugtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity




public class SecurityConfig {

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/profile").permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/").permitAll()
            )
            .csrf(CsrfConfigurer::disable);
        
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }
}