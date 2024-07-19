package com.kkday.svc.kklib.controller;

import com.kkday.svc.kklib.controller.data.LibUserReq;
import com.kkday.svc.kklib.controller.data.LibUserResp;
import com.kkday.svc.kklib.entity.LibUser;
import com.kkday.svc.kklib.service.LibUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class LibUserController {

    @Autowired
    private LibUserService libUserService;

    /**
     * Create User
     * @param req
     * @return
     */
    @PostMapping("/createUser")
    public LibUserResp createUser(@RequestBody LibUserReq req) {
        LibUser user = new LibUser();
        log.info("Create User {}", user);
        BeanUtils.copyProperties(req, user);
        LibUser createdUser = libUserService.createUser(user);
        LibUserResp resp = new LibUserResp();
        resp.setUser(createdUser);
        return resp;
    }

    /**
     * Find User
     * @param userOid
     * @return
     */
    @GetMapping("/findUser/{userOid}")
    public LibUserResp findUser(@PathVariable Integer userOid) {
        LibUser user = libUserService.findUser(userOid);
        LibUserResp resp = new LibUserResp();
        resp.setUser(user);
        log.info("Find User {}", user);
        return resp;
    }

    /**
     * Delete User
     * @param userOid
     */
    @DeleteMapping("/deleteUser/{userOid}")
    public void deleteUser(@PathVariable Integer userOid) {
        LibUser user = libUserService.findUser(userOid);
        log.info("Delete User {}", user);
        libUserService.deleteUser(userOid);
    }

    /**
     * Update User
     * @param req
     * @param userOid
     * @return
     */
    @PutMapping("updateUser/{userOid}")
    public LibUserResp updateUser(@RequestBody LibUserReq req, @PathVariable Integer userOid) {
        LibUser user = libUserService.findUser(userOid);
        log.info("Update User {}", user);
        BeanUtils.copyProperties(req, user);
        LibUser updatedUser = libUserService.updateUser(user);
        LibUserResp resp = new LibUserResp();
        resp.setUser(updatedUser);
        return resp;
    }

    /**
     * List All Users
     * @return
     */
    @GetMapping("listAllUser")
    public List<LibUser> listAllUser() {
        return libUserService.listALLUser();
        // 和book的findAll()的功能相同
    }
}
