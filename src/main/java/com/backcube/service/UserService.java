package com.backcube.service;

import com.backcube.model.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by jimbriglio on 10/9/16.
 */
public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    //User create(User user);

}
