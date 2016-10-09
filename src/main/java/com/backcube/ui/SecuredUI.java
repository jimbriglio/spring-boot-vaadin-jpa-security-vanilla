package com.backcube.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;

import com.vaadin.server.VaadinServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

@SpringUI
// No @Push annotation, we are going to enable it programmatically when the user logs on
@Theme(ValoTheme.THEME_NAME) // Looks nicer
public class SecuredUI extends UI {

    private static final Logger logger = LoggerFactory.getLogger(SecuredUI.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SpringViewProvider viewProvider;

    @Autowired
    ErrorView errorView;

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
    protected void init(VaadinRequest request) {
        getPage().setTitle("BackTrack");
        if (SecurityUtils.isLoggedIn()) {
            showMain();
        } else {
            showLogin();
        }
    }

    private void showLogin() {
        setContent(new LoginForm(this::login));
    }

    private void showMain() {

        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();
        setContent(layout);
//        HorizontalLayout buttons = new HorizontalLayout();
//        buttons.setSpacing(true);
//        layout.addComponent(buttons);
//
//        buttons.addComponent(new Button("Invoke user method", event -> {
//            // This method should be accessible by both 'user' and 'admin'.
//            Notification.show(backendService.userMethod());
//        }));
//        buttons.addComponent(new Button("Navigate to user view", event -> {
//            getNavigator().navigateTo("");
//        }));
//        buttons.addComponent(new Button("Invoke admin method", event -> {
//            // This method should be accessible by 'admin' only.
//            Notification.show(backendService.adminMethod());
//        }));
//        buttons.addComponent(new Button("Navigate to admin view", event -> {
//            getNavigator().navigateTo("admin");
//        }));
//        buttons.addComponent(new Button("Logout", event -> logout()));
//        timeAndUser = new Label();
//        timeAndUser.setSizeUndefined();
//        buttons.addComponent(timeAndUser);
//        buttons.setComponentAlignment(timeAndUser, Alignment.MIDDLE_LEFT);
//
        Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        layout.addComponent(viewContainer);
        layout.setExpandRatio(viewContainer, 1.0f);

        AppView appView = new AppView();
        logger.info("Is Admin: "+((Boolean) SecurityUtils.hasRole("ROLE_ADMIN")).toString());
        if (!SecurityUtils.hasRole("ROLE_ADMIN")) {
            appView.adminButton.setVisible(false);
        }
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        appView.userButton.setCaption(userName);

        appView.logoutButton.addClickListener(event -> logout());
        setContent(layout);
        setErrorHandler(this::handleError);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
        navigator.addProvider(appView);
        navigator.setErrorView(errorView);
        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
        getNavigator().navigateTo("AppView");

    }

    @Override
    public void detach() {
        super.detach();
    }

    private boolean login(String username, String password) {
        try {
            Authentication token = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Reinitialize the session to protect against session fixation attacks. This does not work
            // with websocket communication.
            VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
            SecurityContextHolder.getContext().setAuthentication(token);
            // Now when the session is reinitialized, we can enable websocket communication. Or we could have just
            // used WEBSOCKET_XHR and skipped this step completely.
            getPushConfiguration().setTransport(Transport.WEBSOCKET);
            getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
            // Show the main UI
            showMain();
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }

    private void logout() {
        logger.info("Got here");
        getPage().reload();
        getSession().close();
    }

    private void handleError(com.vaadin.server.ErrorEvent event) {
        Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
        if (t instanceof AccessDeniedException) {
            Notification.show("You do not have permission to perform this operation",
                Notification.Type.WARNING_MESSAGE);
        } else {
            DefaultErrorHandler.doDefault(event);
        }
    }
}
