package com.kkday.svc.kklib.service;

import com.kkday.sdk.svc.BaseDomainService;
import com.kkday.svc.kklib.entity.libUser;

import java.util.List;

public interface libUserService extends BaseDomainService<Integer, libUser> {
    // Process user information

    /**
     * 建立User帳號
     *
     * @param  userOid
     * @return
     */
    //libUser createUser(Integer userOid);

    /**
     *  更新User帳號
     * @param userOid
     * @return
     */
    //libUser updateUser(String userName);

    /**
     * 刪除User帳號
     * @param userOid
     */
    void deleteUser(Integer userOid);

    /**
     * 透過Oid找User
     * @param userOid
     * @return
     */
    libUser findUser(Integer userOid);

    /**
     * 列出所有User
     * @return
     */
    List<libUser> listALLUser();

}
