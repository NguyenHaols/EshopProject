package com.Eshopping.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class RoleDTO {
    private int id;
    private String roleName;
    private Date createDate;
}
