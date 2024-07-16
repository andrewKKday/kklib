package com.kkday.svc.kklib.repository;

import com.kkday.svc.kklib.entity.libUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface libUserRepository extends JpaRepository<libUser, Integer> {
    /** <pre>
     * 依據 Spring Data JPA 的 convention 定義 interface method.
     * Spring Data JPA 將自動生成以下 SQL:
     * <b> SELECT * FROM user WHERE column1=${column1} </b>
     *
     * @param userOid
     * @return
     */
    libUser findByUserOid(Integer userOid);

    libUser findByUserPwd(String userPwd);
}


