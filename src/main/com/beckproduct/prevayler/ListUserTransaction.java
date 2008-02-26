package com.beckproduct.prevayler;

import java.util.Date;

import org.prevayler.TransactionWithQuery;

public class ListUserTransaction implements TransactionWithQuery
{
    private static final long serialVersionUID = 1l;
    
    public ListUserTransaction()
    {
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception
    {
        UserList system = (UserList) prevalentSystem;
        system.print();

        return system;
    }
}
