package com.ensat.speedexam.AuthConfigurations;


import com.ensat.speedexam.AuthConfigurations.AuthEntites.AuthenticationResponse;
import com.ensat.speedexam.AuthConfigurations.AuthEntites.LogInRequest;
import com.ensat.speedexam.AuthConfigurations.AuthEntites.SignUpRequest;
import com.ensat.speedexam.Entites.Role;
import com.ensat.speedexam.Entites.User;
import com.ensat.speedexam.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse signup(SignUpRequest request) {

        // this will be replaced with DTOs or Optional Objects in the next version

        if (request.getFirstname() == null ||
                request.getLastname() == null ||
                request.getEmail() == null ||
                request.getPassword() == null ||
                request.getRole() == null)
            throw new IllegalArgumentException(" ALL FIELDS ARE REQUIRED");

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwToken)
                .build();
    }

    public AuthenticationResponse login(LogInRequest request) {
        // authenticating user using Authentication Provider mechanism
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwToken)
                .build();
    }
}
