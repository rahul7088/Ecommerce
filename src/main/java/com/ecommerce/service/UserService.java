package com.ecommerce.service;

import com.ecommerce.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService extends UserDetailsService {
    User addUser(User user);
    User getOneUser(Long userId);
    List<User> getAllUser();
    User updateUser(User user,Long id);
    String deleteUser(@PathVariable Long id);
}
