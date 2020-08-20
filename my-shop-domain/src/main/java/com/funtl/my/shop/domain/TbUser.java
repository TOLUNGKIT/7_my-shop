package com.funtl.my.shop.domain;

import com.funtl.my.shop.commons.persistence.BaseEntity;

/**
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-08-13 20:42
 **/
public class TbUser extends BaseEntity {
    private String username;
    private String password;
    private String phone;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
