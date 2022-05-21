package com.awesome.repositories;

import com.awesome.pojo.Wife;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WifeRepository extends PagingAndSortingRepository<Wife, Long> {
}
