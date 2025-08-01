package com.green.gallery_jwt_jpa.account;

import com.green.gallery_jwt_jpa.account.model.AccountJoinReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginReq;
import com.green.gallery_jwt_jpa.account.model.AccountLoginRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    // 로그인 ID와 비밀번호로 계정 조회
    AccountLoginRes findByLoginId(AccountLoginReq p);

    // 회원가입 요청을 DB에 저장
    int save(AccountJoinReq p);
}
