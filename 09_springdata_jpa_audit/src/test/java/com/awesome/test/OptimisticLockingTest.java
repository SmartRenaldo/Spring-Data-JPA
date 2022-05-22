package com.awesome.test;

import com.awesome.config.ApplicationConfig;
import com.awesome.pojo.Card;
import com.awesome.pojo.User;
import com.awesome.repositories.UserRepository;
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
    private UserRepository userRepository;

    @Test
    public void testC() {
        Card card = new Card();
        card.setUsername("root");
        card.setPassword("root");

        User user = new User();
        user.setName("Wee");
        user.setCard(card);

        userRepository.save(user);
    }

    @Test
    @Transactional
    @Commit
    public void testU() {
        Optional<User> byId = userRepository.findById(1L);
        byId.get().setName("swing");
    }

}