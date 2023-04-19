package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

 User findByUsername(String username);

 public User findUserById(Long id);
 public List<User> showAll();
 public void updateUser(User user);
 public void deleteUser(Long id);
 public void addUser(User user);
 public List<Role> findRoles();
}
