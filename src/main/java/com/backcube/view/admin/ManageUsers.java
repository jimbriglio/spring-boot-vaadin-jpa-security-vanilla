package com.backcube.view.admin;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.declarative.Design;
import com.vaadin.ui.TextField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class ManageUsers extends VerticalLayout {
    protected TextField firstNameField;
    protected TextField lastNameField;
    protected TextField emailField;
    protected PasswordField passwordField;
    protected ComboBox roleField;
    protected Button createUserButton;

    public ManageUsers() {
        Design.read(this);
    }
}