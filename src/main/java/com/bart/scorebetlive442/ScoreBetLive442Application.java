package com.bart.scorebetlive442;

import com.bart.scorebetlive442.entity.TestEntityRepository;
import com.bart.scorebetlive442.service.TestService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreBetLive442Application {

    @Autowired
    private TestEntityRepository testEntityRepository;

    @Autowired
    private TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(ScoreBetLive442Application.class, args);
        System.out.println("Application started!");
    }

    @PostConstruct
    public void m1() {
//        testService.doMagic();

//        System.out.println("start");
//
//        val testEntity = new TestEntity();
//        testEntity.setMissing("abcd");
//        testEntity.setName("test-2");
//        testEntity.setLazyValue("thisIsLazy");
//
//        val save = testEntityRepository.save(testEntity);
//
//        System.out.println(testEntity);
//        System.out.println(save);
//
//        testEntity.setName("nowyName");
//
//        System.out.println(testEntity);
//        System.out.println(save);
//
//
//        testEntityRepository.save(testEntity);

//        testService.doMagic2();
        testService.lazy1();

        System.out.println("koniec");

    }

}
