package com.beckproduct.prevayler.transaction;

import java.util.Date;

import org.apache.commons.collections.map.ListOrderedMap;
import org.prevayler.TransactionWithQuery;

import com.beckproduct.prevayler.domain.UserList;

public class SaveUserTransaction implements TransactionWithQuery
{
    private static final long serialVersionUID = 1l;

    private final ListOrderedMap users;

    public SaveUserTransaction(ListOrderedMap users)
    {
        this.users = users;
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception
    {
        UserList system = (UserList) prevalentSystem;
        system.save(users);

        return users;
    }
}
