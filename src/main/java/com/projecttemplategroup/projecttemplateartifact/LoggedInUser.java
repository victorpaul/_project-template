package com.projecttemplategroup.projecttemplateartifact;

import com.projecttemplategroup.projecttemplateartifact.security.UserRoleEnum;

// todo, demo user entity, use your own user implementation
@Deprecated
public class LoggedInUser {
    public long id;
    public UserRoleEnum role;

    public LoggedInUser(String username) {
        if (username.contains("super")) {
            this.id = 1;
            this.role = UserRoleEnum.SUPERADMIN;
        } else if (username.contains("admin")) {
            this.id = 2;
            this.role = UserRoleEnum.ADMIN;
        }else{
            this.id = 3;
            this.role = UserRoleEnum.USER;
        }

    }
}
