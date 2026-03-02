package pizaaria.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pizaaria.domain.dto.LoginRequest;
import pizaaria.domain.dto.LoginResponse;
import pizaaria.repository.UserRepository;

import java.time.Instant;

@Controller
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtEncoder jwtEncoder, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> newUser (@RequestBody LoginRequest loginRequest){
        var user = userRepository.findByname(loginRequest.name());

        if (user.isEmpty() || !user.get().isLoginCorret(loginRequest, bCryptPasswordEncoder) ){
            throw new BadCredentialsException("Login ou senha estão errados");
        }

        var now = Instant.now();
        var expiresIn = 400l;

        var clains = JwtClaimsSet.builder()
                .issuer("project")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(clains)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}
