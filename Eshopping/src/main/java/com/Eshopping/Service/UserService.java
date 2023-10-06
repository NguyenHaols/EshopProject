package com.Eshopping.Service;

import com.Eshopping.DTO.UserDTO;
import com.Eshopping.model.User;
import com.Eshopping.payLoad.request.SignUpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface UserService {

    public List<UserDTO> getAllUser();
    public boolean checkLogin(String usernname , String password);
    public boolean addUser(@RequestBody SignUpRequest signUpRequest);
    UserDTO getUserByUsername(String username);
}
