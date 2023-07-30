package com.dev.mercadolivre.config;

import com.dev.mercadolivre.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    public SecurityConfig(
            @Autowired UserRepository userRepository,
            @Autowired CustomUserDetailsService userDetailsService,
            @Autowired JWTUtils jwtUtils){
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    private UserDetailsService userDetailsService;

    private JWTUtils jwtUtils;

    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String[] POST_PERMITED = {
        "/users"
    };

    private static final String[] GET_PERMITED = {
        "/**"
    };

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( userDetailsService ).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                    .disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, POST_PERMITED)
                .permitAll()
                .anyRequest().authenticated();

        http.addFilter(new AuthenticationFilter(authenticationManager(),  userRepository,     jwtUtils));
        http.addFilter(new AuthorizationFilter( authenticationManager(),  userDetailsService , jwtUtils));

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
