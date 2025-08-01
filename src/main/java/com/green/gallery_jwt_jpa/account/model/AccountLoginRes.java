package com.green.gallery_jwt_jpa.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class AccountLoginRes {
    private int id;
    @JsonIgnore
    private String loginPw;
}
