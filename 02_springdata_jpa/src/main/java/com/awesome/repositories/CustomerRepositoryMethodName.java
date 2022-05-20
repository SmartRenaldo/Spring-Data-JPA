package com.awesome.repositories;

import com.awesome.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepositoryMethodName extends PagingAndSortingRepository<Customer, Long> {

    List<Customer> findByName(String name);

    boolean existsByName(String name);

    @Transactional
    @Modifying
    int deleteByName(String name);

    List<Customer> findByNameLike(String nameLike);
}
