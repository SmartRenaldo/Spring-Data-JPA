package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Customer;
import com.awesome.repositories.CustomerRepository;
import com.awesome.repositories.CustomerRepositoryMethodName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JPQLMethodNameTest {

    @Autowired
    CustomerRepositoryMethodName repository;

    @Test
    public void testFindByName() {
        List<Customer> king = repository.findByName("King");
        System.out.println(king);
    }

    @Test
    public void testExistsByName() {
        boolean king = repository.existsByName("King");
        System.out.println(king);
    }

    @Test
    public void testDeleteByName() {
        int row = repository.deleteByName("Dean");
        System.out.println(row);
    }

    @Test
    public void testFindByNameLike() {
        List<Customer> customers = repository.findByNameLike("%i%");
        System.out.println(customers);
    }

}
