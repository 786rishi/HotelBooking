package com.abc.hotel.controller;


import com.abc.hotel.dto.UserRequestDTO;
import com.abc.hotel.dto.UserResponseDTO;
import com.abc.hotel.security.JwtUtil;
import com.abc.hotel.service.InternalUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
* This Controller is responsible to generate JWT tokens
* */
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private InternalUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/getToken")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequestDTO userRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails, userDetailsService.getUserId(userDetails.getUsername()));
        return ResponseEntity.ok(new UserResponseDTO(jwt));
    }
}
