package com.awesome.repositories;

import com.awesome.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepositoryQueryByExample
        extends PagingAndSortingRepository<Customer, Long>
        , QueryByExampleExecutor<Customer> {

}
