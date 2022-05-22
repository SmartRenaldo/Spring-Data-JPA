package com.awesome.repositories;

import com.awesome.pojo.Male;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MaleRepository extends PagingAndSortingRepository<Male, Long> {
}
