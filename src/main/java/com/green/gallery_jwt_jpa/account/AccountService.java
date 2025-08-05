package com.green.gallery_jwt_jpa.account;

import com.green.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginRes;
import com.green.gallery_jwt_jpa.config.model.JwtUser;
import com.green.gallery_jwt_jpa.entity.Members;
import com.green.gallery_jwt_jpa.entity.MembersRoles;
import com.green.gallery_jwt_jpa.entity.MembersRolesIds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;

    public void join(AccountJoinReq req) {
        String hashedPw = BCrypt.hashpw(req.getLoginPw(), BCrypt.gensalt());

        Members members = new Members();
        members.setLoginId(req.getLoginId());
        members.setLoginPw(hashedPw);
        members.setName(req.getName());

        members.addRole("ROLE_USER_1");

        accountRepository.save(members);
    }

    public AccountLoginRes login(AccountLoginReq req) {
        Members members = accountRepository.findByLoginId(req.getLoginId());

        //아이디가 없거나 비밀번호가 다르다면
        if(members == null || !BCrypt.checkpw(req.getLoginPw(), members.getLoginPw())) {
            return null; //return null; 처리
        }
        //로그인 성공!!! 로그인 한 사용자의 role가져오기~
        //호출해주세요
        List<String> roles = members.getRoles().stream().map(item -> item.getMembersRolesIds()
                .getRoleName()
        ).collect(Collectors.toList());
        log.info("roles: {}", roles);
        JwtUser jwtuser = new JwtUser(members.getId(), roles);

        AccountLoginRes accountLoginRes = new AccountLoginRes();
        accountLoginRes.setJwtUser(jwtuser);
        accountLoginRes.setId(members.getId());

        return accountLoginRes;
    }

}