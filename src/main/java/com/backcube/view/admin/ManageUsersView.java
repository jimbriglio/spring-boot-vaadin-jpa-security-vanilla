package com.backcube.view.admin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewProvider;

/**
 * Created by jimbriglio on 10/9/16.
 */
public class ManageUsersView extends ManageUsers implements ViewProvider,View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        // Noop
    }

    @Override
    public String getViewName(String s) {
        return "manageUsersView";
    }

    @Override
    public View getView(String s) {
        return this;
    }
}
