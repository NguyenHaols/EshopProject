package com.Eshopping.Controller;


import com.Eshopping.DTO.UserDTO;
import com.Eshopping.Service.UserService;
import com.Eshopping.model.User;
import com.Eshopping.payLoad.request.SignUpRequest;
import com.Eshopping.payLoad.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServicelmp;


    @RequestMapping("/getAllUser")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userServicelmp.getAllUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public ResponseEntity<?> getUser(@RequestBody UserRequest userRequest){
        String username = userRequest.getUsername();
        UserDTO user = userServicelmp.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
//    @RequestMapping("/role")
//    public ResponseEntity<List<Role>> getAllRole(){
//        List<Role> list2 = new ArrayList<>();
//        roleRepo.findAll().forEach(list2::add);
//        return new ResponseEntity<>(list2, HttpStatus.OK);
//    }


}
