package com.VeebiPood.service.gettersAndSetters;

import java.security.Principal;

public class LoggedInResponse {
    private final String name;
    private final boolean loggedIn;

    public LoggedInResponse(Principal principal) {
        if (principal != null) {
            name = principal.getName();
            loggedIn = true;
        } else {
            name = null;
            loggedIn = false;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
