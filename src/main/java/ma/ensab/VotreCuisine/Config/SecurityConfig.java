package ma.ensab.VotreCuisine.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/utilisateurs/login", "/css/**","/js/**","/img/**", "/h2-console/**").permitAll() // Autoriser l'accès à /login et aux ressources statiques
                .anyRequest().authenticated() // Toute autre URL nécessite une authentification
            )
            .formLogin((form) -> form
                .loginPage("/utilisateurs/login")   // Spécifier l'URL de ta page de login personnalisée
                .permitAll()        // Autoriser l'accès à la page de login
            )
            .logout((logout) -> logout.permitAll())
            .csrf().ignoringRequestMatchers("/h2-console/**");  // IMPORTANT pour H2 Console
            http.headers().frameOptions().sameOrigin(); // IMPORTANT pour H2 Console

        return http.build();
    }
}