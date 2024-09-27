package com.bart.scorebetlive442;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ScoreBetLive442Application {

    public static void main(String[] args) {
        SpringApplication.run(ScoreBetLive442Application.class, args);
        log.debug("Application started!");
    }

}
