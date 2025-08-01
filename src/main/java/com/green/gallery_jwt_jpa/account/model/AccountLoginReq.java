package com.green.gallery_jwt_jpa.account.model;

import lombok.Getter;

@Getter
public class AccountLoginReq {
    private String loginId;
    private String loginPw;
}
