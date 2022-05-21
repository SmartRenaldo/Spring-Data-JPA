package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Customer;
import com.awesome.repositories.CustomerSpecificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpecificationTest {

    @Autowired
    CustomerSpecificationRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testR() {
        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //Root: get column
                //CriteriaBuilder: where
                Path<Long> id = root.get("id");
                Path<String> name = root.get("name");
                Path<String> address = root.get("address");
                Predicate addressP = criteriaBuilder.equal(address, "Sydney");
                Predicate idP = criteriaBuilder.greaterThan(id, 16L);
                CriteriaBuilder.In<String> in = criteriaBuilder.in(name);
                in.value("Isa").value("Opera");
                Predicate and = criteriaBuilder.and(addressP, idP, in);


                return and;
            }
        });

        System.out.println(customers);
    }

    @Test
    public void testR02() {
        Customer customer = new Customer();
        customer.setName("Greenish");
        customer.setAddress("Sydney");
        customer.setId(5L);

        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Long> id = root.get("id");
                Path<String> name = root.get("name");
                Path<String> address = root.get("address");

                ArrayList<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(customer.getAddress())) {
                    predicates.add(criteriaBuilder.equal(address, "Sydney"));
                }

                if (customer.getId() > -1L) {
                    predicates.add(criteriaBuilder.greaterThan(id, 16L));
                }

                if (!StringUtils.isEmpty(customer.getName())) {
                    CriteriaBuilder.In<String> in = criteriaBuilder.in(name);
                    in.value("Isa").value("Opera");
                    predicates.add(in);
                }
                Predicate and = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

                return and;
            }
        });

        System.out.println(customers);

    }

    @Test
    public void testR03() {
        Customer customer = new Customer();
//        customer.setName("Greenish");
        customer.setAddress("Sydney");
//        customer.setId(5L);

        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Long> id = root.get("id");
                Path<String> name = root.get("name");
                Path<String> address = root.get("address");

                ArrayList<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(customer.getAddress())) {
                    predicates.add(criteriaBuilder.equal(address, customer.getAddress()));
                }

                if (customer.getId() != null && customer.getId() > -1L) {
                    predicates.add(criteriaBuilder.equal(id, customer.getId()));
                }

                if (!StringUtils.isEmpty(customer.getName())) {
                    predicates.add(criteriaBuilder.equal(name, customer.getName()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        System.out.println(customers);

    }

    /**
     * equal to name and address
     * greater than id
     * id desc
     */
    @Test
    public void testR04() {
        Customer customer = new Customer();
//        customer.setName("Greenish");
        customer.setAddress("Sydney");
        customer.setId(5L);

        List<Customer> customers = repository.findAll(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Long> id = root.get("id");
                Path<String> name = root.get("name");
                Path<String> address = root.get("address");

                ArrayList<Predicate> predicates = new ArrayList<>();

                if (!StringUtils.isEmpty(customer.getAddress())) {
                    predicates.add(criteriaBuilder.equal(address, customer.getAddress()));
                }

                if (customer.getId() != null && customer.getId() > -1L) {
                    predicates.add(criteriaBuilder.greaterThan(id, customer.getId()));
                }

                if (!StringUtils.isEmpty(customer.getName())) {
                    predicates.add(criteriaBuilder.equal(name, customer.getName()));
                }

                Predicate and = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                Order desc = criteriaBuilder.desc(id);

                return query.where(and).orderBy(desc).getRestriction();
            }
        });

        System.out.println(customers);

    }
}
