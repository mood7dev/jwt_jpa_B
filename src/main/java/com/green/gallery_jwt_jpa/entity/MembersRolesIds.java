package com.green.gallery_jwt_jpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@Embeddable //포함될 수 있는
@AllArgsConstructor
@NoArgsConstructor
public class MembersRolesIds implements Serializable { //복합키 역할의 class는 Serializable을 implements해야 한다.
    private Long memberId;
    @Column(length = 20)
    private String roleName;

    public MembersRolesIds(String roleName) {
        this.roleName = roleName;
    }
}