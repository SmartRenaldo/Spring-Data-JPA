package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Customer;
import com.awesome.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Optional;

//@ContextConfiguration("/spring.xml")
@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJPATest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testC() {
        Customer customer = new Customer();
        customer.setAddress("Adelaide");
        customer.setName("Barrison");
        // save method: no data --> insert, have data --> update
        repository.save(customer);
    }

    @Test
    public void testR() {
        Optional<Customer> byId = repository.findById(2L);
        System.out.println(byId.get());
    }

    @Test
    public void testU() {
        Customer customer = new Customer();
        customer.setId(10L);
        customer.setAddress("Brisbane");
        customer.setName("Nick");
        // save method: no data --> insert, have data --> update
        repository.save(customer);
    }

    @Test
    public void testD() {
        Customer customer = new Customer();
        customer.setId(9L);
        repository.delete(customer);
    }

    @Test
    public void testFindAll() {
        Iterable<Customer> allById = repository.findAllById(Arrays.asList(1L, 2L, 3L, 4L));
        for (Customer customer : allById) {
            System.out.println(customer);
        }
    }

    @Test
    public void testCount() {
        long count = repository.count();
        System.out.println(count);
    }

}
