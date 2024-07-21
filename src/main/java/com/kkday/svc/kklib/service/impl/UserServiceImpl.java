package com.kkday.svc.kklib.service.impl;

import com.kkday.sdk.svc.BaseDomainServiceImpl;
import com.kkday.sdk.mq.MQService;
import com.kkday.svc.kklib.entity.User;
import com.kkday.svc.kklib.mq.UserMQTopic;
import com.kkday.svc.kklib.repository.UserRepository;
import com.kkday.svc.kklib.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends BaseDomainServiceImpl<Integer, User> implements UserService {

    @Autowired
    private UserRepository libRepository;

    @Autowired
    private MQService mqService;

    /**
     * 新增User帳號
     */
    @Override
    public User createUser(User user) {
        User createdUser = libRepository.save(user);
        sendMessage(createdUser);
        return createdUser;
    }

    /**
     * 更新User帳號
     */
    @Override
    public User updateUser(User user) {
        if (user.getUserOid() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User updatedUser = libRepository.save(user);
        sendMessage(updatedUser);
        return updatedUser;
    }

    /**
     * 刪除User帳號
     */
    @Override
    public void deleteUser(Integer userOid) {
        libRepository.deleteById(userOid);
        sendMessage(userOid);
    }

    /**
     * 透過Oid找User
     */
    public User findUser(Integer userOid) {
        return libRepository.findByUserOid(userOid);
    }

    /**
     * 列出所有User
     */
    public List<User> listALLUser() {
        return libRepository.findAll();
    }

    @Override
    protected JpaRepository<User, Integer> getJpaRepository() {
        return null;
    }

    private void sendMessage(Object data) {
        try {
            mqService.sendMessage(UserMQTopic.USER, data);
        } catch (Exception e) {
            log.error("Failed to send MQ message", e);
        }
    }
}
