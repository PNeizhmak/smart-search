package com.db.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.sql.Timestamp;

/**
 * @author Pavel Neizhmak
 */
public class User {

    private Long id;
    private String username;
    private Timestamp lastLoginDate;
    private Timestamp userCreatedDate;
    private int accountStatusId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Timestamp getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(Timestamp userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public int getAccountStatusId() {
        return accountStatusId;
    }

    public void setAccountStatusId(int accountStatusId) {
        this.accountStatusId = accountStatusId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
