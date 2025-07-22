package com.forum.forum_hub.configuration;


import com.forum.forum_hub.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/auth/login", "/auth/register").permitAll()  // Libera login e registro sem autenticação
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();  // Ajuste o método `http.build()` para compatibilidade com a versão do Spring Boot
    }

    // Configura o gerenciador de autenticação
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception { // O gerenciador de autenticação
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class); // Obtenha o gerenciador de autenticação compartilhado
        authenticationManagerBuilder.userDetailsService(userDetailsService) // Define o UserDetailsService
                .passwordEncoder(passwordEncoder()); // Define o PasswordEncoder
        return authenticationManagerBuilder.build();  // Retorna o AuthenticationManager configurado
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usando o BCryptPasswordEncoder para criptografar as senhas
    }
}
