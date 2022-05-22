package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Male;
import com.awesome.pojo.Female;
import com.awesome.repositories.FemaleRepository;
import com.awesome.repositories.MaleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToManyTest {

    @Autowired
    MaleRepository maleRepository;

    @Autowired
    FemaleRepository femaleRepository;

    @Test
    public void testC() {
        List<Female> females = new ArrayList<>();
        females.add(new Female("Carrie"));
        females.add(new Female("Tiffany"));

        Male male = new Male("Danny");
        male.setFemales(females);

        maleRepository.save(male);
    }

    @Test
    @Transactional
    @Commit
    /**
     * if you want to add new association for associated data,
     * you need to get the data from database,
     * or you will get detached data and cannot be persistent
     *
     * without session: the call for a method end, session end
     * with @Transactional: in unit test, default not commit
     */
    public void testC02() {
        List<Female> females = new ArrayList<>();
        females.add(femaleRepository.findById(1L).get());
        females.add(femaleRepository.findById(2L).get());

        Male male = new Male("Mike");
        male.setFemales(females);

        maleRepository.save(male);
    }

    @Test
    public void testR() {
        System.out.println(femaleRepository.findById(2L));
    }

    /**
     * normally, many-to-many is not suitable to delete
     * , cause association relationship is complex
     */
    @Test
    @Transactional
    @Commit
    public void testD() {
        femaleRepository.delete(femaleRepository.findById(1L).get());
    }
}
