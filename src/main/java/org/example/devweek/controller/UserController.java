package org.example.devweek.controller;

import org.example.devweek.domain.model.User;
import org.example.devweek.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController
@RequestMapping(path = "/users")
public class UserController {
  private final UserService service;

  public UserController(final UserService service) {
    this.service = Objects.requireNonNull(service);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<User> findById(@PathVariable final Long id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody final User user) {
    var location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(user.getId())
        .toUri();

    return ResponseEntity.created(location).body(service.create(user));
  }
}
