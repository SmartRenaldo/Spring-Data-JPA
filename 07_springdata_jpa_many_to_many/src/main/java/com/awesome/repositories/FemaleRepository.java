package com.awesome.repositories;

import com.awesome.pojo.Female;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FemaleRepository extends PagingAndSortingRepository<Female, Long> {
}
