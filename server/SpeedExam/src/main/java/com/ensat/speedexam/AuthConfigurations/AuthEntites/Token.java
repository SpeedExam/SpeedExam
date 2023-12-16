package com.ensat.speedexam.AuthConfigurations.AuthEntites;

import com.ensat.speedexam.Entites.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    @ManyToOne(fetch = FetchType.LAZY) // to prevent unnecessary memory overhead
    @JoinColumn(name = "user_id")
    public User user;

}
