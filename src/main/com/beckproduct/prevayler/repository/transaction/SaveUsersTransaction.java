package com.beckproduct.prevayler.repository.transaction;

import java.util.Date;

import org.apache.commons.collections.map.ListOrderedMap;
import org.prevayler.TransactionWithQuery;

import com.beckproduct.prevayler.domain.UserList;

public class SaveUsersTransaction implements TransactionWithQuery
{
    private static final long serialVersionUID = 1l;

    private final ListOrderedMap users;

    public SaveUsersTransaction(ListOrderedMap users)
    {
        this.users = users;
    }

    public Object executeAndQuery(Object prevalentSystem, Date executionTime) throws Exception
    {
        UserList system = (UserList) prevalentSystem;
        system.create(users);

        return users;
    }
}
