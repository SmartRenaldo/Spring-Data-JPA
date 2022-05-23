package com.awesome.service.impl;

import com.awesome.pojo.Visitor;
import com.awesome.repositories.VisitorRepository;
import com.awesome.service.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements IVisitorService {

    @Autowired
    VisitorRepository repository;

    @Override
    public Iterable<Visitor> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean save(Visitor visitor) {
        repository.save(visitor);

        return true;
    }
}
