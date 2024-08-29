package com.bart.scorebetlive442.service;

import com.bart.scorebetlive442.entity.TestEntity;
import com.bart.scorebetlive442.entity.TestEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    @Autowired
    private TestEntityRepository testEntityRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void doMagic() {
        System.out.println("start");

        val testEntity = new TestEntity();
        testEntity.setMissing("abcd");
        testEntity.setName("test-2");

        val save = testEntityRepository.save(testEntity); // w managed

        System.out.println(testEntity);
        System.out.println(save);

        testEntity.setName("nowyName");

        System.out.println(testEntity);
        System.out.println(save);

        testEntity.setName("nowyName");

//        testEntityRepository.save(testEntity);
    }


    //@Transactional
    public void doMagic2() {
        val save = testEntityRepository.findById(1L).get(); // w managed

        entityManager.detach(save);

        save.setName("po-pobraniu");
    }

//    @Transactional
    public void lazy1() {
        val save = testEntityRepository.findById(1L).get();
        System.out.println(save.getName());
        System.out.println(save.getLazyValue());
    }
}
