package com.projecttemplategroup.projecttemplateartifact.controllers;

import com.projecttemplategroup.projecttemplateartifact.LoggedInDemoUser;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class SecurityController {

    public LoggedInDemoUser getSignedInProfile(HttpServletRequest request, HttpServletResponse response) {
        WebContext webContext = new JEEContext(request, response);
        ProfileManager<CommonProfile> manager = new ProfileManager<>(webContext);
        return manager.get(false).get().getAttribute("user", LoggedInDemoUser.class);
    }

}
