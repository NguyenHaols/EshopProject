package com.Eshopping.payLoad.request;

import lombok.Data;

@Data
public class ChangeUserInforRequest {
    private String username;
    private String password;
    private String newPassword;
}
