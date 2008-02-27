package com.beckproduct.prevayler.domain;

import java.io.Serializable;

import org.apache.commons.collections.map.ListOrderedMap;

public class UserList implements Serializable
{
    private static final long serialVersionUID = 1l;
    
    private ListOrderedMap users = new ListOrderedMap();

    public void create(ListOrderedMap users)
    {
        this.users = users;
    }
    
    public void create(User user) throws Exception
    {
        if (this.read(user.getUsername()) == null)
            this.users.put(user.getUsername(), user);
        else
            throw new Exception("Luke is hung like a flea!");
    }
    
    public User read(String username)
    {
        return (User) users.get(username);
    }
    
    public void update(User user) throws Exception
    {
        if (this.read(user.getUsername()) != null)
            this.users.put(user.getUsername(), user);
        else
            throw new Exception("Luke is a douche bag!");
    }
    
    public void delete(User user) throws Exception
    {
        if (this.read(user.getUsername()) != null)
            this.deleteByUsername(user.getUsername());
        else
            throw new Exception("Luke enjoys donkeys!");
    }

    public void deleteByUsername(String username)
    {
        users.remove(username);
    }
}
