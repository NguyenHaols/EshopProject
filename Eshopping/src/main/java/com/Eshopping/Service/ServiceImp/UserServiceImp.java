package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.RoleDTO;
import com.Eshopping.DTO.UserDTO;
import com.Eshopping.Repository.RoleRepo;
import com.Eshopping.Repository.UserRepo;
import com.Eshopping.Service.UserService;
import com.Eshopping.model.Role;
import com.Eshopping.model.User;
import com.Eshopping.payLoad.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUser(){
        List<User> list = userRepo.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(User u : list){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(u.getId());
            userDTO.setUserName(u.getUserName());
            userDTO.setUserCreateDate(u.getCreateDate());
            userDTO.setPassword(u.getPassword());
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(u.getRole().getId());
            roleDTO.setRoleName(u.getRole().getRoleName());
            roleDTO.setCreateDate(u.getRole().getCreateDate());
            userDTO.setRoleDTO(roleDTO);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    @Override
    public boolean checkLogin(String usernname,String password) {

       User user = userRepo.findByUserName(usernname);
       return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Role role = roleRepo.getById(signUpRequest.getRoleId());
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(signUpRequest.getPassWord());
        user.setRole(role);

        try {
            userRepo.save(user);
            return true;
        }catch (Exception e){
            return false;
        }

    }


    @Override
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
