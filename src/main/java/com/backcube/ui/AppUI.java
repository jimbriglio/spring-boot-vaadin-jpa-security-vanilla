package com.backcube.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.UI;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by jimbriglio on 9/25/16.
 */
//@SpringUI
//@RestController
//@RequestMapping("/ui")
//@VaadinSessionScope
//@Theme("mytheme")
public class AppUI extends UI {

    protected Navigator appNavigator;

    @PostConstruct
    public void init() throws IOException {
        ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
        Theme annotation = getUI().getClass().getAnnotation(Theme.class);
        if (annotation != null) {
            String root = servletContext.getRealPath("/");
            if (root != null && Files.isDirectory(Paths.get(root))) {
                Files.createDirectories(Paths.get(servletContext.getRealPath("/VAADIN/themes/" + annotation.value())));
            }
        }
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("Application");
        LoginView loginView = new LoginView();
        AppView appView = new AppView();
        Navigator appNavigator = new Navigator(this, this);

        appNavigator.addView("login", loginView);
        appNavigator.navigateTo("login");
        loginView.loginButton.addClickListener(e->{
            appNavigator.addView("app",appView);
            appNavigator.navigateTo("app");
            });



    }


}
