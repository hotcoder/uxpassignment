package com.uxpsystems.assignment.service.impl;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.repository.UserRepository;
import com.uxpsystems.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    @Override
    @Secured("ROLE_DELETE")
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
