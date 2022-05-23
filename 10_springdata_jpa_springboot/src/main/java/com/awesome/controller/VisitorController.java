package com.awesome.controller;

import com.awesome.pojo.Visitor;
import com.awesome.service.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    IVisitorService visitorService;

    @GetMapping("/all")
    public Iterable<Visitor> getAll(){
        return visitorService.getAll();
    }

    @PostMapping
    public boolean save(@RequestBody Visitor visitor) {
        return visitorService.save(visitor);
    }
}
