package com.Eshopping.payLoad.request;

import lombok.Data;

import java.util.Date;

@Data
public class SignUpRequest {
    private String userName;
    private String passWord;
    private Date createdDate;
    private int roleId;
}
