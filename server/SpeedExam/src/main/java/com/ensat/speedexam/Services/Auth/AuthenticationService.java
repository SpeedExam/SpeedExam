package com.ensat.speedexam.Services.Auth;


import com.ensat.speedexam.AuthConfigurations.AuthEntites.*;
import com.ensat.speedexam.Entites.User;
import com.ensat.speedexam.Repositories.Auth.TokenRepository;
import com.ensat.speedexam.Repositories.UserRepository;
import com.ensat.speedexam.Services.Auth.JwtService;
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
    private final TokenRepository tokenRepository;
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
        var dbsaveduser = userRepository.save(user);
        revokeAllUserTokens(dbsaveduser);
        var jwToken =jwtService.generateToken(user);
        saveUserToken(dbsaveduser, jwToken);
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
        revokeAllUserTokens(user);
        saveUserToken(user, jwToken);
        return AuthenticationResponse.builder()
                .accessToken(jwToken)
                .build();
    }

    private void saveUserToken(User dbsaveduser, String Token) {
        var token = com.ensat.speedexam.AuthConfigurations.AuthEntites.Token.builder()
                .user(dbsaveduser)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .token(Token)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
