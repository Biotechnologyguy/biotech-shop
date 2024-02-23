package com.app.store.service;

import com.app.store.entity.User;
import com.app.store.utils.exceptions.CustomUniqueConstraintViolationException;
import org.springframework.stereotype.Service;



@Service
public interface IAdminService {
    User addUser(User user) throws CustomUniqueConstraintViolationException;
    void deleteUser(Long id);

}
