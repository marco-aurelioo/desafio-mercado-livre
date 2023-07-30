package com.dev.mercadolivre.config;

import com.dev.mercadolivre.repository.UserRepository;
import com.dev.mercadolivre.repository.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public AuthenticationFilter(
            AuthenticationManager authenticationManager,
            UserRepository  userRepository,
            JWTUtils jwtUtils){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

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
                return new UsernamePasswordAuthenticationToken(user.getId(),user.getPassword());
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
        Integer id = (Integer) authResult.getPrincipal();
        String token = jwtUtils.generateToken(id);
        response.addHeader("Authorization", "Bearer " + token);
    }

}
