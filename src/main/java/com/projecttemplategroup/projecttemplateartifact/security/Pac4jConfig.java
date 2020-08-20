package com.projecttemplategroup.projecttemplateartifact.security;

import com.projecttemplategroup.projecttemplateartifact.LoggedInUser;
import com.projecttemplategroup.projecttemplateartifact.security.authenticators.SignInAuthenticator;
import org.pac4j.core.authorization.authorizer.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.direct.AnonymousClient;
import org.pac4j.core.config.Config;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.http.client.direct.DirectBasicAuthClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.pac4j.http.client.direct.CookieClient;

@Configuration
public class Pac4jConfig {

    @Bean
    public Config config() {
        AnonymousClient anonymmous = new AnonymousClient();
        DirectBasicAuthClient baseAuth = new DirectBasicAuthClient(new SignInAuthenticator());
        CookieClient tokenClient = new CookieClient();

        Config config = new Config(anonymmous, baseAuth, tokenClient);

        config.addAuthorizer(UserRoleEnum.USER.name(), new RequireAnyRoleAuthorizer(UserRoleEnum.USER.name()));
        config.addAuthorizer(UserRoleEnum.ADMIN.name(), new RequireAnyRoleAuthorizer(UserRoleEnum.ADMIN.name()));
        config.addAuthorizer(UserRoleEnum.SUPERADMIN.name(), new RequireAnyRoleAuthorizer(UserRoleEnum.SUPERADMIN.name()));
        return config;
    }

    public static CommonProfile getProfile(LoggedInUser user){
        CommonProfile profile = new CommonProfile();
        profile.addRole(user.role.name());
        profile.addAttribute("user", user);
        return profile;
    }
}
