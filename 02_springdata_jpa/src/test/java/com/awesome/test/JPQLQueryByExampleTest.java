package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Customer;
import com.awesome.repositories.CustomerRepositoryMethodName;
import com.awesome.repositories.CustomerRepositoryQueryByExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JPQLQueryByExampleTest {

    @Autowired
    CustomerRepositoryQueryByExample repository;

    // find all customers that name is "King" and do not care about address
    @Test
    public void test01() {
        Customer customer = new Customer();
        customer.setName("King");
        customer.setAddress(null);
        Example<Customer> example = Example.of(customer);
        Iterable<Customer> customers = repository.findAll(example);
        System.out.println(customers);
    }

    @Test
    public void test02() {
        Customer customer = new Customer();
        customer.setName("I");
        customer.setAddress("Beijing");
        ExampleMatcher matcher = ExampleMatcher.matching()
                //ignore address attribute
                .withIgnorePaths("address")
//                .withIgnoreCase("name")
//                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)   works for all fields
//                .withMatcher("name", m -> m.contains())   only works for "name", "%I%"
                .withMatcher("name",
                        ExampleMatcher.GenericPropertyMatcher::contains).withIgnoreCase(); //"%I%"
        Example<Customer> example = Example.of(customer, matcher);
        Iterable<Customer> customers = repository.findAll(example);
        System.out.println(customers);
    }

}
