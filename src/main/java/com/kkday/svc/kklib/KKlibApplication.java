package com.kkday.svc.kklib;

import com.kkday.sdk.annotation.EnableKKdaySdk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKKdaySdk
public class KKlibApplication {

    public static void main(String[] args) {
        SpringApplication.run(KKlibApplication.class, args);
    }

}
