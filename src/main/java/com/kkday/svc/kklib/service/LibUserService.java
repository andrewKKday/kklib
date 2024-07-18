package com.kkday.svc.kklib.service;

import com.kkday.sdk.svc.BaseDomainService;
import com.kkday.svc.kklib.entity.LibUser;

import java.util.List;

public interface LibUserService extends BaseDomainService<Integer, LibUser> {

    // Process user information
    LibUser createUser(LibUser users);

    //更新User帳號
    LibUser updateUser(LibUser users);

    void deleteUser(Integer userOid);

    LibUser findUser(Integer userOid);

    List<LibUser> listALLUser();

}
