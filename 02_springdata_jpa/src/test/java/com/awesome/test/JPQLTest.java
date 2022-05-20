package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Customer;
import com.awesome.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JPQLTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testFindCustomerByName() {
        List<Customer> customers = repository.findCustomerByName("Soul");
        System.out.println(customers);
    }

    /**
     * native SQL query
     */
    @Test
    public void testFindCustomerByNameNQ() {
        List<Customer> customers = repository.findCustomerByNameNQ("Soul");
        System.out.println(customers);
    }

    @Test
    public void testFindCustomerByAddress() {
        List<Customer> customers = repository.findCustomerByAddress("Brisbane");
        System.out.println(customers);
    }

    @Test
    public void testUpdateCustomerNameById() {
        int king = repository.updateCustomerNameById("King", 11L);
        System.out.println(king);
    }

    @Test
    public void testDeleteCustomerByName() {
        int nill = repository.deleteCustomerByName("nill");
        System.out.println(nill);
    }

    @Test
    public void testInsertCustomerBySelect() {
        int i = repository.insertCustomerBySelect(11L);
        System.out.println(i);
    }

}
