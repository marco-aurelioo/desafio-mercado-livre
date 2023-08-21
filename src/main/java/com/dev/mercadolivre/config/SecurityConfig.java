package com.dev.mercadolivre.config;

import com.dev.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {


    public SecurityConfig(
            @Autowired UserRepository userRepository,
            @Autowired CustomUserDetailsService userDetailsService,
            @Autowired AuthenticationConfiguration authenticationConfiguration,
            @Autowired JWTUtils jwtUtils){
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    private AuthenticationConfiguration authenticationConfiguration;
    private UserDetailsService userDetailsService;

    private JWTUtils jwtUtils;

    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                .csrf().disable()

                .antMatcher("/**").authorizeRequests()
                    .antMatchers(
                        "/users/**",
                        "/login/**",
                        "/auth/**").permitAll()
                    .anyRequest().authenticated();

        httpSecurity.addFilter(new AuthenticationFilter(authenticationManager(authenticationConfiguration),  userRepository,   passwordEncoder(),   jwtUtils));
        httpSecurity.addFilter(new AuthorizationFilter( authenticationManager(authenticationConfiguration),  userDetailsService , jwtUtils));
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration );
        return source;
    }


}
