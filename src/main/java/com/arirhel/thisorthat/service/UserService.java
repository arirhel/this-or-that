package com.arirhel.thisorthat.service;

import com.arirhel.thisorthat.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDetails save(User user);

}
