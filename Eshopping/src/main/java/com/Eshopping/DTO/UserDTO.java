package com.Eshopping.DTO;

import com.Eshopping.model.Role;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private int userId;
    private String userName;
    private String password;
    private Date userCreateDate;
    private RoleDTO role;
}
