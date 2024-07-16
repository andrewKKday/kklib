package com.kkday.svc.kklib;

import com.kkday.sdk.annotation.EnableKKdaySdkTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "test")
@SpringBootTest(classes = KklibApplication.class)
@EnableKKdaySdkTest
class KklibApplicationTests {

    @Test
    void contextLoads() {
    }
}
