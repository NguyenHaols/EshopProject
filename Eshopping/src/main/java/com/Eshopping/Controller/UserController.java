package com.Eshopping.Controller;


import com.Eshopping.DTO.UserDTO;
import com.Eshopping.Repository.UserRepo;
import com.Eshopping.Service.UserService;
import com.Eshopping.model.User;
import com.Eshopping.payLoad.ResponseData;
import com.Eshopping.payLoad.request.ChangeUserInforRequest;
import com.Eshopping.payLoad.request.UserRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServicelmp;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userServicelmp.getAllUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public ResponseEntity<?> getUser(@RequestBody UserRequest userRequest){
        String username = userRequest.getUsername();
        UserDTO user = userServicelmp.getUserByUsername(username);
        ResponseData responseData = new ResponseData();
        if (user == null) {
            return ResponseEntity.badRequest().body("Can't find the user");
        }else {
            responseData.setData(user);
            responseData.setSucces(true);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody ChangeUserInforRequest change){
        ResponseData responseData = new ResponseData();
        String username = change.getUsername();
        String password = change.getPassword();
        UserDTO user = userServicelmp.getUserByUsername(username);
        if(user == null){
            return ResponseEntity.badRequest().body("Can't find the user");
        }
        if(userServicelmp.checkLogin(username,password)){
            String newpassword = change.getNewPassword();
            userServicelmp.updatePasswordByUsername(user,newpassword);
            responseData.setSucces(true);
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    }
//    @RequestMapping("/role")
//    public ResponseEntity<List<Role>> getAllRole(){
//        List<Role> list2 = new ArrayList<>();
//        roleRepo.findAll().forEach(list2::add);
//        return new ResponseEntity<>(list2, HttpStatus.OK);
//    }


}
