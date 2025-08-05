package com.green.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class MembersRoles {
    @EmbeddedId
    private MembersRolesIds membersRolesIds;

    //관계설정
    //CascadeType.PERSIST 영속성 전이
    //CascadeType.REMOVE 부모 영속성이 delete되면 본인도 delete
    @ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REMOVE })
    @MapsId("memberId")
    private Members members;
}
