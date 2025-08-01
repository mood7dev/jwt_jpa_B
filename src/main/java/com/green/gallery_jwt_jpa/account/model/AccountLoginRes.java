package com.green.gallery_jwt_jpa.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gallery_jwt_jpa.config.model.JwtUser;
import lombok.Getter;

@Getter
public class AccountLoginRes {
    private int id;
    @JsonIgnore
    private String loginPw;
    @JsonIgnore
    private JwtUser jwtUser;
}
