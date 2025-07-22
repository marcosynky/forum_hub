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
    private UserDetailsServiceImpl userDetailsService;  // Injetando o UserDetailsService

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuração do filtro de segurança
        http
                .authorizeRequests()
                .requestMatchers("/auth/login", "/auth/register").permitAll()  // Permite acesso às rotas de login e registro sem autenticação
                .anyRequest().authenticated()  // Requer autenticação para outras rotas
                .and()
                .csrf().disable();  // Desabilita o CSRF (Cross-Site Request Forgery)

        return http.build();  // Retorna a configuração de segurança
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // Configuração do AuthenticationManager para autenticação
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        // Configurando o UserDetailsService no AuthenticationManagerBuilder
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());  // Configura o PasswordEncoder para autenticação

        return authenticationManagerBuilder.build();  // Retorna o AuthenticationManager configurado
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Configura o PasswordEncoder (para criptografar e verificar senhas)
        return new BCryptPasswordEncoder();
    }
}
