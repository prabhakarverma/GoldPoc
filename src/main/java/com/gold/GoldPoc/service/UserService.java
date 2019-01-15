package com.gold.GoldPoc.service;

import com.gold.GoldPoc.model.User;
import com.gold.GoldPoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author Prabhakar Verma
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional(propagation = Propagation.REQUIRED)
    public User createUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    public User userLogin(User user) {
        User usr = null;
        if (user.getName() != null && user.getPassword() != null) {
            usr = userRepository.findByUserNameAndPassword(user.getName(), user.getPassword());
            if (null != usr) {
                System.out.println("Create Date: " + user.getCreateTime());
                usr.setCreateTime(new java.sql.Date(new Date().getTime()));
                userRepository.save(usr);
            }
            return usr;
        }
        return usr;
    }

    public User resetPassword(User user, String pass) {
        User usr = userRepository.findByUserNameAndPassword(user.getName(), user.getPassword());
        usr.setPassword(pass);
        userRepository.save(usr);
        return usr;
    }

    public User forgotPassword(User user) {
        User usr = userRepository.findByEmail(user.getEmail());
        return usr;
    }
}