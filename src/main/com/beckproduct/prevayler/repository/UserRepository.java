package com.beckproduct.prevayler.repository;

import org.apache.commons.collections.map.ListOrderedMap;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import com.beckproduct.prevayler.domain.User;
import com.beckproduct.prevayler.domain.UserList;
import com.beckproduct.prevayler.repository.transaction.DeleteUserTransaction;
import com.beckproduct.prevayler.repository.transaction.ReadUserTransaction;
import com.beckproduct.prevayler.repository.transaction.SaveUserTransaction;
import com.beckproduct.prevayler.repository.transaction.SaveUsersTransaction;
import com.beckproduct.prevayler.repository.transaction.UpdateUserTransaction;

public class UserRepository
{
    private Prevayler prevayler;

    public UserRepository() throws Exception
    {
        prevayler = PrevaylerFactory.createPrevayler(new UserList(), "conf/journal");
    }

    public void create(ListOrderedMap users) throws Exception
    {
        prevayler.execute(new SaveUsersTransaction(users));
    }

    public void create(User user) throws Exception
    {
        prevayler.execute(new SaveUserTransaction(user));
    }

    public User read(String username) throws Exception
    {
        return (User) prevayler.execute(new ReadUserTransaction(username));
    }

    public void update(User user) throws Exception
    {
        prevayler.execute(new UpdateUserTransaction(user));
    }

    public void delete(String userName) throws Exception
    {
        prevayler.execute(new DeleteUserTransaction(userName));
    }

    public UserList list()
    {
        return (UserList) prevayler.prevalentSystem();
    }
}
