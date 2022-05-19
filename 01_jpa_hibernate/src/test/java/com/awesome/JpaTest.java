package com.awesome;

import com.awesome.pojo.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    EntityManagerFactory factory;

    @Before
    public void before(){
        factory = Persistence.createEntityManagerFactory("hibernateJPA");
//        factory = Persistence.createEntityManagerFactory("openJpa");
    }

    @Test
    public void testC() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = new Customer();
        customer.setName("Hike");

        em.persist(customer);

        tx.commit();
    }

    @Test
    public void testQ() {
        EntityManager em = factory.createEntityManager();

        Customer customer = em.find(Customer.class, 5L);

        System.out.println(customer);
    }

    @Test
    public void testU() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = new Customer();
        customer.setId(5L);
        customer.setName("Mike");

        /**
         * merge: when specify primary key, check if there are changes first. if nothing changed, do not update
         *        when did not specify primary key, do not update
         */
        em.merge(customer);

        tx.commit();
    }

    @Test
    public void testU_JPQL() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = new Customer();
        customer.setId(5L);
        customer.setName("Mike");

        String jpql = "UPDATE Customer set name=:name where id=:id";
        em.createQuery(jpql)
                .setParameter("name", "John")
                .setParameter("id", 5L)
                .executeUpdate();

        tx.commit();
    }

    @Test
    public void testU_SQL() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = new Customer();
        customer.setId(5L);
        customer.setName("Mike");

        String sql = "UPDATE t_customer set name=:name where id=:id";
        em.createNativeQuery(sql)
                .setParameter("name", "Jesus")
                .setParameter("id", 5L)
                .executeUpdate();

        tx.commit();
    }

    @Test
    public void testD() {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Customer customer = em.find(Customer.class, 5L);
        customer.setId(5L);

        em.remove(customer);

        tx.commit();
    }
}
