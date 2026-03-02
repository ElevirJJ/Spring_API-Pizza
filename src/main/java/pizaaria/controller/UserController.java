package pizaaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pizaaria.domain.dto.CreateUser;
import pizaaria.domain.entity.User;
import pizaaria.repository.UserRepository;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> newUser (@RequestBody CreateUser dto){
         var newUser = userRepository.findByname(dto.name());

         if (newUser.isPresent()){
             throw new RuntimeException("usuario nao existir");
         }

        var user = new User();
         user.setName(dto.name());
         user.setPassword(bCryptPasswordEncoder.encode(dto.password()));
         userRepository.save(user);

         return ResponseEntity.ok().build();
    }
}
