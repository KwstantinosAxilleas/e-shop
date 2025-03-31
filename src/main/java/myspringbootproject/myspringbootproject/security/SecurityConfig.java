package myspringbootproject.myspringbootproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import lombok.AllArgsConstructor;
import myspringbootproject.myspringbootproject.security.filter.AuthenticationFilter;
import myspringbootproject.myspringbootproject.security.filter.ExceptionHandlerFilter;
import myspringbootproject.myspringbootproject.security.filter.JWTAuthorizationFilter;
import myspringbootproject.myspringbootproject.security.manager.CustomAuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");

        http
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // Allow H2 console
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults()) // Ensure CORS is enabled
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/h2/**").permitAll()
                .requestMatchers("/v3/api-docs", "/v3/api-docs/**", "/swagger-ui/index.html", "/swagger-ui.html",
                                 "/swagger-ui/**", "/swagger-resources/**", "/swagger-resources",
                                 "/v3/api-docs/swagger-config").permitAll()
                .requestMatchers(HttpMethod.POST, "/consumer/register").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}