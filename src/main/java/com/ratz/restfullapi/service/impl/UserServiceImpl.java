package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.model.User;
import com.ratz.restfullapi.repository.UserRepository;
import com.ratz.restfullapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class.getSimpleName());

  @Autowired
  private UserRepository userRepository;


  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    LOG.info("Finding User...");

    User user = userRepository.findByUsername(username);

    if (user != null) {
      return user;

    } else {

      throw new UsernameNotFoundException("User not found with the provided id.");
    }
  }
}

