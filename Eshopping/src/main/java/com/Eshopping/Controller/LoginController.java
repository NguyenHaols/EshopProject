package com.Eshopping.Controller;

import com.Eshopping.Service.UserService;
import com.Eshopping.payLoad.ResponseData;
import com.Eshopping.payLoad.request.SignInRequest;
import com.Eshopping.payLoad.request.SignUpRequest;
import com.Eshopping.utils.JwtUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    UserService userServicelmp;

    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping("/user")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userServicelmp.getAllUser(), HttpStatus.OK);
    }



    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public ResponseEntity<?> signIn (@RequestBody SignInRequest signInRequest){
        ResponseData responseData = new ResponseData();
        String username = signInRequest.getUsername();
        String password = signInRequest.getPassword();
        if(userServicelmp.checkLogin(username,password)){
            String token = jwtUtil.generateToken(username);
            responseData.setData(token);
            responseData.setSucces(true);
        }else {
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    };


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp (@RequestBody SignUpRequest signUpRequest ){
        ResponseData responseData = new ResponseData();
        if(userServicelmp.addUser(signUpRequest)){
            responseData.setData(true);
        }else {
            responseData.setData(false);
        }
        return new ResponseEntity<>(responseData,HttpStatus.OK);
    };
}
