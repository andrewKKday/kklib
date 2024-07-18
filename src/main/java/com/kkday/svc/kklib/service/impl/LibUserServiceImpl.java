package com.kkday.svc.kklib.service.impl;

import com.kkday.sdk.annotation.KKLock;
import com.kkday.sdk.annotation.KKTxRequired;
import com.kkday.sdk.svc.BaseDomainServiceImpl;
import com.kkday.svc.kklib.entity.LibUser;
import com.kkday.svc.kklib.repository.LibUserRepository;
import com.kkday.svc.kklib.service.LibUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LibUserServiceImpl extends BaseDomainServiceImpl<Integer, LibUser> implements LibUserService {

    @Autowired
    private LibUserRepository libRepository;

    //所以所有需要修改 DB 資料的 Service Method 都需要調整為 REQUIRED / REQUIRED_NEW

    /**
     * 新增User帳號
     */
    @KKTxRequired
    @KKLock(lockName = "createUserLock", tryLockTime = 5000L) //L == long int
    @Override
    public LibUser createUser(LibUser users){
        return libRepository.save(users);
    }

    /**
     *  更新User帳號
     */
    @KKTxRequired
    @KKLock(lockName = "updateUserLock", tryLockTime = 5000L)
    @Override
    public LibUser updateUser(LibUser users){
        return libRepository.save(users);
    }

    /**
     * 刪除User帳號
     */
    @KKTxRequired
    @KKLock(lockName = "deleteUserLock", tryLockTime = 5000L)
    @Override
    public void deleteUser(Integer userOid){
        libRepository.deleteById(userOid);
    }

    /**
     * 透過Oid找User
     */
    public LibUser findUser(Integer userOid){
        return libRepository.findByUserOid(userOid);
    }

    /**
     * 列出所有User
     */
    public List<LibUser> listALLUser(){
        return libRepository.findAll();
    }

    @Override
    protected JpaRepository<LibUser, Integer> getJpaRepository() {
        return null;
    }
}
