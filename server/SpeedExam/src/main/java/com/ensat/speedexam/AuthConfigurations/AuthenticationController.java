package com.ensat.speedexam.AuthConfigurations;



import com.ensat.speedexam.AuthConfigurations.AuthEntites.AuthenticationResponse;
import com.ensat.speedexam.AuthConfigurations.AuthEntites.LogInRequest;
import com.ensat.speedexam.AuthConfigurations.AuthEntites.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authservice;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
            @RequestBody SignUpRequest request
    )
    {
        return ResponseEntity.ok(authservice.signup(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LogInRequest request
    ) {
        return ResponseEntity.ok(authservice.login(request));
    }
}
