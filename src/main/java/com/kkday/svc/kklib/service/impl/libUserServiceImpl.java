package com.kkday.svc.kklib.service.impl;

import com.kkday.sdk.annotation.KKTimeout;
import com.kkday.sdk.annotation.KKTxRequired;
import com.kkday.sdk.svc.BaseDomainServiceImpl;
import com.kkday.svc.kklib.entity.libUser;
import com.kkday.svc.kklib.repository.libUserRepository;
import com.kkday.svc.kklib.service.libUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class libUserServiceImpl extends BaseDomainServiceImpl<Integer, libUser> implements libUserService {

    private libUserRepository libRepository;

    //所以所有需要修改 DB 資料的 Service Method 都需要調整為 REQUIRED / REQUIRED_NEW
    /** TODO : create&update implement
     @Override
    @KKTxRequired
    @KKTimeout(10)
    public libUser createUser(String userOid, String userPwd){
        libUser newUser = libRepository.findByUserOid(userOid);
        return newUser;
    }*/

    /**
     * 更新User帳號
    @Override
    @KKTxRequired
    @KKTimeout(10)
    public libUser updateUser(Integer userOid){
        return null;
    }*/

    /**
     * 刪除User帳號
     *
     * @param userOid
     */
    @Override
    @KKTxRequired
    @KKTimeout(10)
    public void deleteUser(Integer userOid){
        libRepository.deleteById(userOid);
    }

    /**
     * 透過Oid找User
     *
     * @param userOid
     * @return
     */
    public libUser findUser(Integer userOid){
        libUser newUser = libRepository.findByUserOid(userOid);
        return newUser;
    }

    /**
     * 列出所有User
     *
     * @return
     */
    public List<libUser> listALLUser(){
        return libRepository.findAll();
    }

    @Override
    protected JpaRepository<libUser, Integer> getJpaRepository() {
        return null;
    }
}
