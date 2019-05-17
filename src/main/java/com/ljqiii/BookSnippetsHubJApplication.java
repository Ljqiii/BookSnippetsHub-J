package com.ljqiii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BookSnippetsHubJApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookSnippetsHubJApplication.class, args);
    }

}
