package com.kkday.svc.kklib.mq.data;

import com.kkday.svc.kklib.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserMessage {
    Integer userOid;
    User user;
}
