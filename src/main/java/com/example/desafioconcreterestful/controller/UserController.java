package com.example.desafioconcreterestful.controller;

import com.example.desafioconcreterestful.dto.LoginDTO;
import com.example.desafioconcreterestful.dto.ProfileDTO;
import com.example.desafioconcreterestful.dto.UserDTO;
import com.example.desafioconcreterestful.entities.User;
import com.example.desafioconcreterestful.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping()
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        UserDTO responseUser = userService.createUser(user);

        return ResponseEntity.ok().body(responseUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {

        UserDTO userDTO = userService.login(loginDTO);

        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping("/profile")
    public ResponseEntity<UserDTO> profile(@RequestBody ProfileDTO profileDTO) {

        UserDTO userDTO = userService.profile(profileDTO);

        return ResponseEntity.ok().body(userDTO);
    }
}