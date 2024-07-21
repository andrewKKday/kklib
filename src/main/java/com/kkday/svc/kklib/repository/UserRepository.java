package com.kkday.svc.kklib.repository;

import com.kkday.svc.kklib.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /** <pre>
     * 依據 Spring Data JPA 的 convention 定義 interface method.
     * Spring Data JPA 將自動生成以下 SQL:
     * <b> SELECT * FROM user WHERE column1=${column1} </b>
     */
    User findByUserOid(Integer userOid);
}


