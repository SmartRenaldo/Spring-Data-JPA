package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.QCustomer;
import com.awesome.repositories.CustomerRepositoryQueryDSL;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryDSLTest {

    @Autowired
    CustomerRepositoryQueryDSL repository;

    @Test
    public void testEQ(){
        QCustomer customer = QCustomer.customer;

        BooleanExpression eq = customer.id.eq(10L);

        System.out.println(repository.findOne(eq));
    }

    /**
     * find customer id > 5, name = "Isa" | "Nill"
     */
    @Test
    public void testAnd(){
        QCustomer customer = QCustomer.customer;

        BooleanExpression and = customer.id.gt(5L)
                .and(customer.name.in("Isa", "Nill"))
                .and(customer.address.eq("London"));

        System.out.println(repository.findAll(and));
    }

}
