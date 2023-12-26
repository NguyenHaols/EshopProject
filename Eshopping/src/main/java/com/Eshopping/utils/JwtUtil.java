package com.Eshopping.utils;

import com.Eshopping.DTO.UserDTO;
import com.Eshopping.Service.ServiceImp.CustomUserDetailService;
import com.Eshopping.Service.UserService;
import com.Eshopping.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.net.http.HttpRequest;

@Component
public class JwtUtil {

    @Value("${privateKey}")
    private String privateKey;

    @Autowired
    CustomUserDetailService userService;

    public String generateToken(String username){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().setSubject(username).signWith(key).compact();
        return jws;
    };

    public Authentication parseTokenToUser(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(token == null){
            return null;
        }

        try {
            String username = Jwts.parser()
                    .setSigningKey(privateKey)
                    .parseClaimsJws(token.replace("Bearer",""))
                    .getBody()
                    .getSubject();
            UserDTO user = userService.getUserByUsername(username);

            return username != null ? new UsernamePasswordAuthenticationToken
                    (user.getUserName(),null, AuthorityUtils.createAuthorityList(user.getRole().getRoleName()))
                    : null;
        }catch (Exception e){
            return null;
        }
    };
}
