package ma.ensab.VotreCuisine.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     // Pour encrypter le password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/utilisateurs/login", "/utilisateurs/signup", "/css/**","/js/**","/img/**", "/h2-console/**", "/search", "/recette/**","/").permitAll() // Autoriser l'accès à /login et aux ressources statiques
                .requestMatchers("/admin/**").hasAuthority("admin")
                .anyRequest().authenticated() // Toute autre URL nécessite une authentification
            )
            .formLogin((form) -> form
                .loginPage("/utilisateurs/login")   // Spécifier l'URL de ta page de login personnalisée
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()        // Autoriser l'accès à la page de login
                .defaultSuccessUrl("/")
                .failureUrl("/utilisateurs/login?error=true")
            )
            .logout((logout) -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/utilisateurs/logout"))  //  pour le bouton logout
                    .logoutSuccessUrl("/utilisateurs/login") // Re-diriger vers la page de connexion après la déconnexion
                    .permitAll())
            .csrf().ignoringRequestMatchers("/h2-console/**");  // IMPORTANT pour H2 Console
            http.headers().frameOptions().sameOrigin(); // IMPORTANT pour H2 Console

        return http.build();
    }
}