package com.awesome.repositories;

import com.awesome.pojo.Customer;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepositoryQueryDSL extends
        PagingAndSortingRepository<Customer, Long>
        , QuerydslPredicateExecutor<Customer> {


}
