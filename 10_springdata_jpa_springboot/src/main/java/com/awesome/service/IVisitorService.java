package com.awesome.service;

import com.awesome.pojo.Visitor;

public interface IVisitorService {
    Iterable<Visitor> getAll();

    boolean save(Visitor visitor);
}
