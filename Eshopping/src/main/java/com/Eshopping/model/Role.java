package com.Eshopping.model;

import com.Eshopping.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "roles")
public class Role {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "role")
    List<User> userList;


}
