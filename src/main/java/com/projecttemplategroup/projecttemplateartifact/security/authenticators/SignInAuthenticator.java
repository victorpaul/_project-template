package com.projecttemplategroup.projecttemplateartifact.security.authenticators;

import com.projecttemplategroup.projecttemplateartifact.LoggedInDemoUser;
import com.projecttemplategroup.projecttemplateartifact.exceptions.UnauthorizedException;
import com.projecttemplategroup.projecttemplateartifact.security.Pac4jConfig;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;

public class SignInAuthenticator implements Authenticator<UsernamePasswordCredentials> {

    private void throwsException(String message){
        throw new UnauthorizedException(message);
    }

    @Override
    public void validate(UsernamePasswordCredentials credentials, WebContext context) {
        if (credentials == null) throwsException("No credential");

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        if (new EmailValidator().isValid(username, null)) {
            if(password.equals("qwerty")){

                // todo, its demo sign in logic, use your own implementation with DB
                LoggedInDemoUser user = new LoggedInDemoUser(username);

                credentials.setUserProfile(Pac4jConfig.getProfile(user));
            }else{
                throwsException("Password doesn't much to password qwerty");
            }
        }else{
            throwsException("Username is invalid email");
        }
    }

}
