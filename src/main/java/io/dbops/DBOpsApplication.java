package io.dbops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.dbops")
public class DBOpsApplication {

    public static void main(String[] args) {

        SpringApplication.run(DBOpsApplication.class, args);
    }

}

