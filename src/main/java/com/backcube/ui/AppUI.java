package com.backcube.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.spring.boot.annotation.EnableVaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jimbriglio on 9/25/16.
 */
@SpringUI
@RestController
@RequestMapping("/ui")
@VaadinSessionScope
@Theme("valo")
public class AppUI extends UI {


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout layout = new VerticalLayout();
        Button button = new Button("Click me");

        setContent(layout);
        layout.addComponent(button);

    }


}
