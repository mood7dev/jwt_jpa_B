package com.green.gallery_jwt_jpa.config.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class JwtUser {
    private long signedUserId;
    private List<String> roles;
    /*
    role 이름은 ROLE_아무거나
    인가 처리 ROLE_USER, ROLE_ADMIN, _ROLE_MAMAGER, ROLE_LEVEL_1...
     */
}
