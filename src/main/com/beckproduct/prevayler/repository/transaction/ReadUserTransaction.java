package com.beckproduct.prevayler.repository.transaction;

import java.util.Date;

import org.prevayler.TransactionWithQuery;

import com.beckproduct.prevayler.domain.UserList;

public class ReadUserTransaction implements TransactionWithQuery
{
    private static final long serialVersionUID = 1l;
    
    private String username;
    
    public ReadUserTransaction(String username)
    {
        this.username = username;
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception
    {
        UserList system = (UserList) prevalentSystem;

        return system.read(username);
    }
}
