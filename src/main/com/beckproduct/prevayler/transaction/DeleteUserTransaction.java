package com.beckproduct.prevayler.transaction;

import java.util.Date;

import org.prevayler.Transaction;

import com.beckproduct.prevayler.domain.UserList;

public class DeleteUserTransaction implements Transaction
{
    private static final long serialVersionUID = 1l;
    
    private final String userName;

    public DeleteUserTransaction(String userName)
    {
        this.userName = userName;
    }

    public void executeOn(Object prevalentSystem, Date executionTime)
    {
        UserList system = (UserList) prevalentSystem;
        system.remove(userName);
    }
}
