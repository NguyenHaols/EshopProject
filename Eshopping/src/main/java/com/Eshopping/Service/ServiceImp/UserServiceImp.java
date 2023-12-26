package com.Eshopping.Service.ServiceImp;

import com.Eshopping.DTO.RoleDTO;
import com.Eshopping.DTO.UserDTO;
import com.Eshopping.Repository.RoleRepo;
import com.Eshopping.Repository.UserRepo;
import com.Eshopping.Service.UserService;
import com.Eshopping.model.Role;
import com.Eshopping.model.User;
import com.Eshopping.payLoad.request.SignUpRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepo.findByUserName(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }



    public List<UserDTO> getAllUser(){
        List<User> list = userRepo.findAll();
        List<UserDTO> userDTOList = list.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return userDTOList;
    };

    @Override
    public boolean checkLogin(String usernname,String password) {

       User user = userRepo.findByUserName(usernname);
       return passwordEncoder.matches(password,user.getPassword());
    };

    @Override
    public boolean addUser(SignUpRequest signUpRequest) {
        Role role = roleRepo.getById(3);
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassWord()));
        user.setRole(role);

        try {
            userRepo.save(user);
            return true;
        }catch (Exception e){
            return false;
        }

    };



    @Transactional
    public void updatePasswordByUsername(UserDTO userDTO,String newpassword) {
        entityManager.createNativeQuery("UPDATE users SET password = :newPassword WHERE user_name = :username")
                .setParameter("newPassword", passwordEncoder.encode(newpassword))
                .setParameter("username", userDTO.getUserName())
                .executeUpdate();
    };






}
