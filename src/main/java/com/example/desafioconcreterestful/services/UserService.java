package com.example.desafioconcreterestful.services;

import com.example.desafioconcreterestful.dto.LoginDTO;
import com.example.desafioconcreterestful.dto.ProfileDTO;
import com.example.desafioconcreterestful.dto.UserDTO;
import com.example.desafioconcreterestful.entities.User;

public interface UserService {

    UserDTO createUser(User pUser);

    UserDTO login(LoginDTO pLoginDTO);

    UserDTO profile(ProfileDTO pProfileDTO);
}
