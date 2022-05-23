package com.awesome.repositories;

import com.awesome.pojo.Visitor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VisitorRepository extends PagingAndSortingRepository<Visitor, Long> {
}
