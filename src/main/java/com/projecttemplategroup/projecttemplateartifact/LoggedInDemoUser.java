package com.projecttemplategroup.projecttemplateartifact;


import com.projecttemplategroup.projecttemplateartifact.enums.UserRoleEnum;
import com.projecttemplategroup.projecttemplateartifact.interfaces.UserWithRole;

// todo, demo user entity, use your own user implementation that implements UserWithRole
@Deprecated
public class LoggedInDemoUser implements UserWithRole {
    private long id;
    private UserRoleEnum role;

    public LoggedInDemoUser(String username) {
        if (username.contains("admin")) {
            this.id = 2;
            this.role = UserRoleEnum.ADMIN;
        } else if (username.contains("user")) {
            this.id = 3;
            this.role = UserRoleEnum.USER;
        } else {
            this.id = -1;
            this.role = null;
        }
    }

    @Override
    public UserRoleEnum getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "LoggedInDemoUser{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }

}
