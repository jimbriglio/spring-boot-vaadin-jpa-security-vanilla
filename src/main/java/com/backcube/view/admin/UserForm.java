package com.backcube.view.admin;

/**
 * Created by jimbriglio on 10/10/16.
 */

import com.backcube.model.Role;
import com.backcube.model.User;
import com.backcube.service.UserService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.EnumSet;

@UIScope
@SpringComponent
public class UserForm extends AbstractForm<User> {

    @Autowired
    ApplicationContext applicationContext;

    EventBus.UIEventBus eventBus;
    UserService repo;

    TextField email = new MTextField("Email");
    TextField firstName = new MTextField("First Name");
    TextField lastName = new MTextField("Last Name");
    TextField password = new MTextField("Password");
    EnumSet<Role> roles = EnumSet.of(Role.ROLE_ADMIN, Role.ROLE_USER);

    public ComboBox role = new ComboBox("Role", roles);

    public UserForm(UserService r) {
        setSizeUndefined();
        role.setValue(Role.ROLE_USER);
        this.repo = r;


        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(user -> {
            user = new User(email.getValue(), firstName.getValue(), lastName.getValue(), password.getValue(), Role.valueOf(role.getValue().toString()));
            // persist changes
            repo.create(user);
            // send the event for other parts of the application
            //eventBus.publish(this, new UserModifiedEvent(user));
        });
        //setResetHandler(p -> eventBus.publish(this, new UserModifiedEvent(p)));

    }

    @Override
    public Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        email,
                        firstName,
                        lastName,
                        password,
                        role
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

}
