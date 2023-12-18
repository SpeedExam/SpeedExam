package com.ensat.speedexam.AuthConfigurations.AuthEntites;

import com.ensat.speedexam.Entites.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class SignUpRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

}
