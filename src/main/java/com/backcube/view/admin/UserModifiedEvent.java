package com.backcube.view.admin;

import com.backcube.model.User;

import javax.annotation.PreDestroy;
import java.io.Serializable;

/**
 * Created by jimbriglio on 10/10/16.
 */
public class UserModifiedEvent implements Serializable {

    private final User user;

    public UserModifiedEvent(User p) {
        this.user = p;
    }

    public User getUser() {
        return user;
    }


}
