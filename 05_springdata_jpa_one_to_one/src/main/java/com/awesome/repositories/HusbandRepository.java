package com.awesome.repositories;

import com.awesome.pojo.Husband;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HusbandRepository extends PagingAndSortingRepository<Husband, Long> {
}
