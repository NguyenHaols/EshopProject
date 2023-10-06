package com.Eshopping.payLoad;

import lombok.Data;

@Data
public class ResponseData {
    private int status = 200;
    private Object data;
    private String desc;
    private boolean isSucces;
}
