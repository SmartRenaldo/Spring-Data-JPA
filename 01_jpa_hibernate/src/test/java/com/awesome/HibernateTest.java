package com.awesome;

import com.awesome.pojo.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class HibernateTest {
    private SessionFactory sessionFactory;

    @Before
    public void init() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("/hibernate.cfg.xml").build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testC() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Customer customer = new Customer();
            customer.setName("Jerry");
            customer.setAddress("London");
            session.save(customer);
            tx.commit();
        }
    }

    @Test
    public void testRFind() {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.find(Customer.class, 1L);
            System.out.println(customer);
        }
    }

    @Test
    public void testRLoad() {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.load(Customer.class, 1L);
            System.out.println(customer);
        }
    }

    @Test
    public void testU() {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Customer customer = new Customer();
            customer.setName("Karrie");
            session.saveOrUpdate(customer);
            tx.commit();
        }
    }

    @Test
    public void testRHql() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "From Customer where id=:id";
            List<Customer> customers = session.createQuery(hql, Customer.class)
                    .setParameter("id", 2L)
                    .getResultList();

            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
}
