package com.app.store.service.impl;

import com.app.store.entity.User;
import com.app.store.repository.IUserRepository;
import com.app.store.service.CustomUserDetailsService;
import com.app.store.service.IAdminService;
import com.app.store.utils.exceptions.CustomUniqueConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import static com.app.store.utils.Constants.*;


@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private IUserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    @Override
    public User addUser(User user) throws CustomUniqueConstraintViolationException{
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            String rootMsg = e.getRootCause().getMessage();
            System.out.println(rootMsg);
            if (rootMsg.contains(UNIQUE_USERNAME_CONSTRAINT)) {
                throw new CustomUniqueConstraintViolationException("Username is already taken");
            } else if (rootMsg.contains(UNIQUE_EMAIL_CONSTRAINT)) {
                throw new CustomUniqueConstraintViolationException("Email is already taken");
            } else if (rootMsg.contains(UNIQUE_MOBILE_NUMBER_CONSTRAINT)) {
                throw new CustomUniqueConstraintViolationException("Mobile number is already taken");
            }
            throw new CustomUniqueConstraintViolationException("Unknown field error");
        }
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
