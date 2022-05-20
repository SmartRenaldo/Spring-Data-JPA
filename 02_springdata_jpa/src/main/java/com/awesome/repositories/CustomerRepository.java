package com.awesome.repositories;

import com.awesome.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    /**
     * index
     * @param name
     * @return
     */
    @Query("FROM Customer where name=?1 ")
    List<Customer> findCustomerByName(String name);

    /**
     * native query
     * @param name
     * @return
     */
    @Query(value = "select * FROM t_customer where name=?1", nativeQuery = true)
    List<Customer> findCustomerByNameNQ(String name);

    /**
     * specific name
     * @param address
     * @return
     */
    @Query("FROM Customer where address=:address ")
    List<Customer> findCustomerByAddress(@Param("address") String address);

    @Query("UPDATE Customer set name=:name where id=:id")
    @Modifying  //modify spring data this is CUD not R
    @Transactional
    int updateCustomerNameById(@Param("name") String name, @Param("id")Long id);

    @Query("DELETE FROM Customer where name=?1 ")
    @Modifying  //modify spring data this is CUD not R
    @Transactional
    int deleteCustomerByName(String name);

    /**
     * insert the selected data (only works in hibernate)
     * @param id
     * @return
     */
    @Query("INSERT INTO Customer(name) SELECT name FROM Customer where id=?1 ")
    @Modifying  //modify spring data this is CUD not R
    @Transactional
    int insertCustomerBySelect(Long id);

}
