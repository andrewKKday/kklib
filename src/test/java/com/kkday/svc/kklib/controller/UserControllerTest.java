package com.kkday.svc.kklib.controller;

import com.kkday.sdk.annotation.EnableKKdaySdkTest;
import com.kkday.svc.kklib.KKlibApplication;
import com.kkday.svc.kklib.controller.data.UserReq;
import com.kkday.svc.kklib.controller.data.UserResp;
import com.kkday.svc.kklib.entity.User;
import com.kkday.svc.kklib.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = KKlibApplication.class)
@EnableKKdaySdkTest
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private User user;
    private UserReq userReq;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setUserOid(1);
        user.setUserPwd("TestPwd");

        userReq = new UserReq();
        BeanUtils.copyProperties(user, userReq);
    }

    @Test
    public void testCreateUser() {
        Mockito.when(userService.createUser(any(User.class))).thenReturn(user);

        UserResp response = userController.createUser(userReq);
        assertEquals(user.getUserOid(), response.getUser().getUserOid());
        assertEquals(user.getUserPwd(), response.getUser().getUserPwd());
    }

    @Test
    public void testUpdateUser() {
        Mockito.when(userService.findUser(anyInt())).thenReturn(user);
        Mockito.when(userService.updateUser(any(User.class))).thenReturn(user);

        UserResp response = userController.updateUser(userReq, 1);
        assertEquals(user.getUserOid(), response.getUser().getUserOid());
        assertEquals(user.getUserPwd(), response.getUser().getUserPwd());
    }

    @Test
    public void testDeleteUser() {
        Mockito.when(userService.findUser(anyInt())).thenReturn(user);
        Mockito.doNothing().when(userService).deleteUser(anyInt());

        userController.deleteUser(1);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(1);
    }

    @Test
    public void testFindUser() {
        Mockito.when(userService.findUser(anyInt())).thenReturn(user);

        UserResp response = userController.findUser(1);
        assertEquals(user.getUserOid(), response.getUser().getUserOid());
        assertEquals(user.getUserPwd(), response.getUser().getUserPwd());
    }

    @Test
    public void testListAllUser() {
        Mockito.when(userService.listALLUser()).thenReturn(List.of(user));

        List<User> users = userController.listAllUser();
        assertEquals(1, users.size());
        assertEquals(user.getUserOid(), users.getFirst().getUserOid());
        assertEquals(user.getUserPwd(), users.getFirst().getUserPwd());
    }
}
