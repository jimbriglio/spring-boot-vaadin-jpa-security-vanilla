package com.backcube.view;

import com.backcube.service.UserService;
import com.backcube.service.UserServiceImpl;
import com.backcube.view.admin.UserForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Created by jimbriglio on 9/28/16.
 */
@SpringComponent
@Configurable
public class AppView extends ApplicationDesign implements ViewProvider, View {

    UserService userService;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        System.out.println("enter");
        adminButton.addClickListener(evt -> this.content.addComponent(new UserForm(userService)));
    }

    @Override
    public String getViewName(String s) {
        return "appView";
    }

    @Override
    public View getView(String s) {
        return this;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
