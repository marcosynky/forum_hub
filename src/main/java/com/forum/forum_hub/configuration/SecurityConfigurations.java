package com.forum.forum_hub.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final UserDetailsService userDetailsService; // Não precisa de @Autowired no campo

    public SecurityConfigurations(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService; // Injeção via construtor
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers("/login/**", "/register/**").permitAll()
                .requestMatchers("/topicos/**", "/usuarios/**").authenticated()
                .requestMatchers("/actuator/**").permitAll()
                .and()
                .formLogin() // Usar formulário de login
                .loginPage("/login") // Página customizada para login (se necessário)
                .permitAll()
                .and()
                .userDetailsService(userDetailsService);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Criação do encoder BCrypt para senhas
    }
}
