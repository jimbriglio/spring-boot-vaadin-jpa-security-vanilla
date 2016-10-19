package com.backcube.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;

/**
 * Created by jimbriglio on 9/28/16.
 */
public class LoginView extends LoginDesign implements ViewProvider,View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        // Noop
    }

    @Override
    public String getViewName(String s) {
        return "loginView";
    }

    @Override
    public View getView(String s) {
        return this;
    }
}
