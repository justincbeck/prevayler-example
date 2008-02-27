package com.beckproduct.prevayler.domain;

import java.io.Serializable;

public class User implements Serializable
{
    private static final long serialVersionUID = 1l;
    
    private String username;
    
    private String password;

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}
