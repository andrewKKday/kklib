package com.kkday.svc.kklib.entity;

import com.kkday.sdk.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
// 將此類標註為 JPA 實體類
@Data
// 自動生成 getter、setter、toString、equals 和 hashCode 方法
@EqualsAndHashCode(callSuper = true)
// 包含父類屬性的 equals 和 hashCode 方法，主鍵由數據庫生成
@ToString(callSuper = true)
// 包含父類屬性的 toString 方法
@Accessors(chain = true)
// 使 setter 方法返回當前對象，支持鏈式調用
@Table(name = "lib_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userOid;

    private String userPwd;

}
