package com.backcube.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;

public class LoginForm extends LoginView{

    public LoginForm(LoginCallback callback) {

        login.addClickListener( evt -> {
            String pword = password.getValue();
            password.setValue("");
            if (!callback.login(username.getValue(), pword)) {
                Notification.show("Login failed");
                username.focus();
            }
        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);

    }

    @FunctionalInterface
    public interface LoginCallback {

        boolean login(String username, String password);
    }
}
