package com.green.gallery_jwt_jpa.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Members extends Created {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String loginPw;

    @OneToMany(mappedBy = "members", cascade = CascadeType.ALL)
    private List<MembersRoles> roles = new ArrayList<>();

    public void addRole(String roleName) {
        MembersRolesIds membersRolesIds = new MembersRolesIds(roleName);
        MembersRoles membersRoles = new MembersRoles();
        membersRoles.setMembers(this);
        membersRoles.setMembersRolesIds(membersRolesIds);

        this.roles.add(membersRoles);
    }
}