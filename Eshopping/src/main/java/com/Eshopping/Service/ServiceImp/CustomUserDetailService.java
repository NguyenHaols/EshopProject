package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.RoleDTO;
import com.Eshopping.DTO.UserDTO;
import com.Eshopping.Repository.UserRepo;
import com.Eshopping.model.User;
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
        UserDTO userDTO = new UserDTO();
        RoleDTO roleDTO = new RoleDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserCreateDate(user.getCreateDate());
        roleDTO.setId(user.getRole().getId());
        roleDTO.setRoleName(user.getRole().getRoleName());
        roleDTO.setCreateDate(user.getRole().getCreateDate());
        userDTO.setRoleDTO(roleDTO);

        return userDTO;
    };
}
