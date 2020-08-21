package com.projecttemplategroup.projecttemplateartifact.security.authenticators;

import com.projecttemplategroup.projecttemplateartifact.LoggedInDemoUser;
import com.projecttemplategroup.projecttemplateartifact.security.Pac4jConfig;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.TokenCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.util.CommonHelper;

public class SessionAuthenticator implements Authenticator<TokenCredentials> {

    @Override
    public void validate(TokenCredentials credentials, WebContext context) {
        if (credentials == null) throwsException("Credentials must not be null");
        if (CommonHelper.isBlank(credentials.getToken())) throwsException("Token must not be blank");

        // todo, it's demo, change it to your own implementation
        if (!credentials.getToken().contains("test-token-")) {
            throwsException("User not found");
        }
        credentials.setUserProfile(Pac4jConfig.getProfile(new LoggedInDemoUser(credentials.getToken())));
    }

    protected void throwsException(String message) {
        throw new CredentialsException(message);
    }

}
