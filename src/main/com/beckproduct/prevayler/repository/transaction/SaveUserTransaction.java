package com.beckproduct.prevayler.repository.transaction;

import java.util.Date;

import org.prevayler.TransactionWithQuery;

import com.beckproduct.prevayler.domain.User;
import com.beckproduct.prevayler.domain.UserList;

public class SaveUserTransaction implements TransactionWithQuery
{
    private static final long serialVersionUID = 1l;

    private final User user;

    public SaveUserTransaction(User user)
    {
        this.user = user;
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception
    {
        UserList system = (UserList) prevalentSystem;
        system.create(user);
        
        return user;
    }
}
