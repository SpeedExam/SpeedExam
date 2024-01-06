package com.ensat.speedexam.AuthConfigurations.AuthEntites;

import com.ensat.speedexam.Entites.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  @JsonProperty("ACCESS_TOKEN")
  private String accessToken;
  private Role role;
}
