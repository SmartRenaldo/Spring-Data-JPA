package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Customer;
import com.awesome.pojo.QCustomer;
import com.awesome.repositories.CustomerRepositoryQueryDSL;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QueryDSLTest {

    @Autowired
    CustomerRepositoryQueryDSL repository;

    @Test
    public void testEQ() {
        QCustomer customer = QCustomer.customer;

        BooleanExpression eq = customer.id.eq(10L);

        System.out.println(repository.findOne(eq));
    }

    /**
     * find customer id > 5, name = "Isa" | "Nill"
     */
    @Test
    public void testAnd() {
        QCustomer customer = QCustomer.customer;

        BooleanExpression and = customer.id.gt(5L)
                .and(customer.name.in("Isa", "Nill"))
                .and(customer.address.eq("London"));

        System.out.println(repository.findAll(and));
    }

    @Test
    public void testDynamicSearch() {
        Customer params = new Customer();
        params.setName("Greenish, Isa, Opera, Tira");
        params.setAddress("Sydney");
        params.setId(5L);

        // initialised condition, always works
        QCustomer customer = QCustomer.customer;

        BooleanExpression expression = customer.isNotNull().or(customer.isNull());

        expression = (params.getId() != null && params.getId() > -1) ?
                expression.and(customer.id.gt(params.getId())):expression;
        expression = StringUtils.hasText(params.getName()) ?
                expression.and(customer.name.in(params.getName().split(", "))):expression;
        expression = StringUtils.hasText(params.getAddress()) ?
                expression.and(customer.address.eq(params.getAddress())):expression;

        System.out.println(repository.findAll(expression));
    }

    //@PersistenceContext could replace @Autowired to solve thread safety issue
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void testNativeSQL() {
        Customer params = new Customer();
        params.setName("Greenish, Isa, Opera, Tira");
        params.setAddress("Sydney");
        params.setId(5L);

        JPAQueryFactory factory = new JPAQueryFactory(entityManager);

        QCustomer customer = QCustomer.customer;

        JPAQuery<Tuple> tuple = factory.select(customer.count(), customer.id, customer.name)
                .from(customer)
                .where(customer.id.gt(params.getId()))
                .groupBy(customer.address)
                .orderBy(customer.id.desc());

        for (Tuple fetch : tuple.fetch()) {
            System.out.println(fetch.get(customer.count()) + " " + fetch.get(customer.id) + " " + fetch.get(customer.name));
        }
    }

}
