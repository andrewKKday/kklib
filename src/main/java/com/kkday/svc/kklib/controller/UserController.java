package com.kkday.svc.kklib.controller;

import com.kkday.sdk.annotation.KKLock;
import com.kkday.sdk.annotation.KKTxRequired;
import com.kkday.svc.kklib.controller.data.UserReq;
import com.kkday.svc.kklib.controller.data.UserResp;
import com.kkday.svc.kklib.entity.User;
import com.kkday.svc.kklib.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增User帳號
     * @param req
     * @return
     */
    @KKTxRequired
    @PostMapping("/createUser")
    public UserResp createUser(@RequestBody UserReq req) {
        User user = new User();
        log.info("Create User {}", user);
        BeanUtils.copyProperties(req, user);
        User createdUser = userService.createUser(user);
        UserResp resp = new UserResp();
        resp.setUser(createdUser);
        return resp;
    }

    /**
     * 更新User帳號
     * @param req
     * @param userOid
     * @return
     */
    @KKTxRequired
    @KKLock(lockName = "updateUserLock", tryLockTime = 5000L)
    @PutMapping("/updateUser/{userOid}")
    public UserResp updateUser(@RequestBody UserReq req, @PathVariable Integer userOid) {
        User user = userService.findUser(userOid);
        log.info("Update User {}", user);
        // 保存原始識別符
        Integer originalId = user.getUserOid();

        // 拷貝屬性，確保不改變識別符
        BeanUtils.copyProperties(req, user);

        // 恢復原始識別符
        user.setUserOid(originalId);
        User updatedUser = userService.updateUser(user);
        UserResp resp = new UserResp();
        resp.setUser(updatedUser);
        return resp;
    }

    /**
     * 刪除User帳號
     * @param userOid
     */
    @KKTxRequired
    @KKLock(lockName = "deleteUserLock", tryLockTime = 5000L)
    @DeleteMapping("/deleteUser/{userOid}")
    public void deleteUser(@PathVariable Integer userOid) {
        User user = userService.findUser(userOid);
        log.info("Delete User {}", user);
        userService.deleteUser(userOid);
    }

    /**
     * 透過Oid找User
     * @param userOid
     * @return
     */
    @GetMapping("/findUser/{userOid}")
    public UserResp findUser(@PathVariable Integer userOid) {
        User user = userService.findUser(userOid);
        UserResp resp = new UserResp();
        resp.setUser(user);
        log.info("Find User {}", user);
        return resp;
    }

    /**
     * 列出所有Users
     * @return
     */
    @GetMapping("/listAllUser")
    public List<User> listAllUser() {
        return userService.listALLUser();
        // 和book的findAll()的功能相同
    }
}
