package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Account;
import com.awesome.pojo.Client;
import com.awesome.repositories.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@ContextConfiguration(classes = ApplicationConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class OptimisticLockingTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testC() {
        Account account = new Account();
        account.setUsername("zhang");
        account.setPassword("root");

        Client client = new Client();
        client.setName("zhang");
        client.setAccount(account);

        clientRepository.save(client);
    }

    @Test
    @Transactional
    @Commit
    public void testU() {
        Optional<Client> byId = clientRepository.findById(1L);
        byId.get().setName("earing");
    }

}