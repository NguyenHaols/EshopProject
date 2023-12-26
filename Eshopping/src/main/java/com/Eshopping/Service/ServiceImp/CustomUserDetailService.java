package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.RoleDTO;
import com.Eshopping.DTO.UserDTO;
import com.Eshopping.Repository.UserRepo;
import com.Eshopping.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found with username " + username);
        }
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().getRoleName()));
    }

    public UserDTO getUserByUsername(String username) {
        User user = userRepo.findByUserName(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    };
}
