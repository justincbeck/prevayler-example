package com.beckproduct.prevayler.domain;

import java.io.Serializable;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.ListOrderedMap;

public class UserList implements Serializable
{
    private static final long serialVersionUID = 1l;

    private ListOrderedMap users = new ListOrderedMap();

    public void save(ListOrderedMap users)
    {
        this.users = users;
    }

    public void remove(String userName)
    {
        users.remove(userName);
    }

    public void print()
    {
        MapIterator it = users.mapIterator();

        while (it.hasNext())
        {
            Object key = it.next();
            Object value = it.getValue();
            System.out.println("KEY " + key + " VALUE : " + value);
        }
    }

    public ListOrderedMap getUsers()
    {
        return users;
    }
}
