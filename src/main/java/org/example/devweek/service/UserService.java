package org.example.devweek.service;

import org.example.devweek.domain.model.User;

public interface UserService {
  User findById(Long id);

  User create(User user);
}
