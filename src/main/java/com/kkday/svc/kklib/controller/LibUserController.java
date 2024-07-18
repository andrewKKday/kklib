package com.kkday.svc.kklib.controller;

import com.kkday.svc.kklib.entity.LibUser;
import com.kkday.svc.kklib.service.LibUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class LibUserController {
    @Autowired
    private LibUserService libUserService;

    @PostMapping("/createUser")
    public LibUser createUser(@RequestBody LibUser libUser) {
        return libUserService.createUser(libUser);
    }

    @GetMapping("/findUser/{userOid}")
    public LibUser findUser(@RequestBody LibUser libUser, @PathVariable Integer userOid) {
        LibUser user = libUserService.findUser(userOid);
        log.info("Find User {}", user);
        return libUserService.findUser(libUser.getUserOid());
    }

    @DeleteMapping("/deleteUser/{userOid}")
    public void deleteUser(@PathVariable Integer userOid) {
        LibUser user = libUserService.findUser(userOid);
        log.info("Delete User {}", user);
        libUserService.deleteUser(userOid);
    }

    @PutMapping("updateUser/{userOid}")
    public LibUser updateUser(@RequestBody LibUser libUser, @PathVariable Integer userOid) {
        LibUser user = libUserService.findUser(userOid);
        log.info("Update User {}", user);
        return libUserService.updateUser(libUser);
    }

    @GetMapping("listAllUser")
    public List<LibUser> listAllUser() {
        return libUserService.listALLUser();
    }
}
