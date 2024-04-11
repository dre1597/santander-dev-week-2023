package org.example.devweek.service.impl;

import org.example.devweek.domain.model.User;
import org.example.devweek.domain.repository.UserRepository;
import org.example.devweek.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository repository;
  private final UserRepository userRepository;

  public UserServiceImpl(final UserRepository repository, UserRepository userRepository) {
    this.repository = Objects.requireNonNull(repository);
    this.userRepository = userRepository;
  }

  @Override
  public User findById(final Long id) {
    return repository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public User create(final User user) {
    if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
      throw new IllegalArgumentException("This account number already exists");
    }

    return repository.save(user);
  }
}
