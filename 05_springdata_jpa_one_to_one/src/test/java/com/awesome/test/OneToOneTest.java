package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Husband;
import com.awesome.pojo.Wife;
import com.awesome.repositories.HusbandRepository;
import com.awesome.repositories.WifeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OneToOneTest {

    @Autowired
    WifeRepository repository;

    @Test
    public void testC() {
        Husband husband = new Husband();
        husband.setName("Trump");
        husband.setAge(65);

        Wife wife = new Wife();
        wife.setName("Melenia");
        wife.setAge(47);
        wife.setHusband(husband);

        repository.save(wife);
    }

    @Test
    /**
     * why Transactional for FetchType LAZY?
     * After query (without transaction), session will close immediately.
     * After query (with transaction), session will not close immediately.
     */
    @Transactional(readOnly = true)
    public void testR() {
        Optional<Wife> byId = repository.findById(2L);
        System.out.println(byId);
    }

    @Test
    public void testD() {
        repository.deleteById(2L);
    }

    @Test
    public void testU() {
        Husband husband = new Husband();
        husband.setId(8L);
        husband.setName("Wolf");
        Wife wife = new Wife();
        wife.setId(5L);
        wife.setName("aYa");
        repository.save(wife);
    }

}
