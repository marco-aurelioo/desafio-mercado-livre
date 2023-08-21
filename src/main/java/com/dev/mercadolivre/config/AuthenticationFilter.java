package com.dev.mercadolivre.config;

import com.dev.mercadolivre.repository.UserRepository;
import com.dev.mercadolivre.repository.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    Logger log = Logger.getLogger(AuthenticationFilter.class.getName());

    public AuthenticationFilter(
            AuthenticationManager authenticationManager,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder,
            JWTUtils jwtUtils){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private UserRepository  userRepository;

    private JWTUtils jwtUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
        HttpServletResponse response) {
            try{
                ObjectMapper objectMapper = new ObjectMapper();
                UserLogin loginRequest = objectMapper.readValue(request.getInputStream(), UserLogin.class);
                UserEntity user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
                return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(),loginRequest.getPassword()));
            }catch (Exception ex){
                throw  new BadCredentialsException(ex.getMessage());
            }
    }

    @Override
    public void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) {
        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
        try{
            String token = jwtUtils.generateToken(userDetails.getId());
            log.info("successfulAuthentication "+token);
            response.addHeader("Authorization", "Bearer " + token);
        }catch( Exception ex){
            throw  new BadCredentialsException(ex.getMessage());
        }
    }

}
