package com.kkday.svc.kklib.service;

import com.kkday.sdk.svc.BaseDomainService;
import com.kkday.svc.kklib.entity.User;

import java.util.List;

public interface UserService extends BaseDomainService<Integer, User> {

    /**
     *  建立User帳號
      */
    User createUser(User users);

    /**
     *  更新User帳號
     */
    User updateUser(User users);

    /**
     *  刪除User帳號
     */
    void deleteUser(Integer userOid);

    /**
     *  透過Oid找User
     */
    User findUser(Integer userOid);

    /**
     *  列出所有User
     */
    List<User> listALLUser();

}
