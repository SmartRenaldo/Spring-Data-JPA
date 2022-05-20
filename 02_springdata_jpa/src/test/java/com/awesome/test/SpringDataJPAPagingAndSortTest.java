package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Customer;
import com.awesome.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringDataJPAPagingAndSortTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testPaging() {
        Page<Customer> all = repository.findAll(PageRequest.of(0, 2));

        for (Customer customer : all) {
            System.out.println(customer);
        }

        System.out.println(all.getTotalPages());
        System.out.println(all.getTotalElements());
    }

    @Test
    public void testSort() {
        Iterable<Customer> id = repository.findAll(Sort.by("id").descending());
        System.out.println(id);
    }

    @Test
    public void testSortTypeSafeAPI() {
        Sort.TypedSort<Customer> sortType = Sort.sort(Customer.class);
        Sort sort = sortType.by(Customer::getId).descending();
        Iterable<Customer> all = repository.findAll(sort);
        System.out.println(all);
    }

}
