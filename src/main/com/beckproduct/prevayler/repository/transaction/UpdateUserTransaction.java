package com.beckproduct.prevayler.repository.transaction;

import java.util.Date;

import org.prevayler.TransactionWithQuery;

import com.beckproduct.prevayler.domain.User;
import com.beckproduct.prevayler.domain.UserList;

public class UpdateUserTransaction implements TransactionWithQuery
{
    private static final long serialVersionUID = 1l;
    
    private User user;

    public UpdateUserTransaction(User user)
    {
        this.user = user;
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception
    {
        UserList system = (UserList) prevalentSystem;
        system.update(user);
        
        return user;
    }
}
