package com.shemhazaicraft.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShemhazaiCraftApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShemhazaiCraftApplication.class, args);
    }

}
