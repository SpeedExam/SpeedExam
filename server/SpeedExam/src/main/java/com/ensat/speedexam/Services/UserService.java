package com.ensat.speedexam.Services;

import com.ensat.speedexam.AuthConfigurations.AuthEntites.ChangePasswordRequest;
import com.ensat.speedexam.Entites.User;
import com.ensat.speedexam.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) throws Exception {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // Password checks
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
            throw new IllegalAccessException("WRONG PASSWORD");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalAccessException("NOT MATCHING PASSWORDS");
        }

        user.setPassword(passwordEncoder.encode(request.getConfirmationPassword()));
        userRepository.save(user);
    }
}

