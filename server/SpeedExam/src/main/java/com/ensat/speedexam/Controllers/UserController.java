package com.ensat.speedexam.Controllers;

import com.ensat.speedexam.AuthConfigurations.AuthEntites.ChangePasswordRequest;
import com.ensat.speedexam.Services.Auth.LogoutService;
import com.ensat.speedexam.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/cp")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(LogoutService.class);

    // Changing password endpoint
    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) throws Exception {
        userService.changePassword(request,connectedUser);
        logger.info("PASSWORD CHANGED");
        return ResponseEntity.ok().build();
    }

}
