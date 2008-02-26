package com.beckproduct.prevayler;

import java.io.IOException;

import org.apache.commons.collections.map.ListOrderedMap;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

public class UserDAO
{
    private Prevayler prevayler;
    
    private UserList list;

    public UserDAO()
    {
        try
        {
            prevayler = PrevaylerFactory.createPrevayler(new UserList(), "conf/journal");
            list = (UserList) prevayler.prevalentSystem();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void save(ListOrderedMap users)
    {
        try
        {
            prevayler.execute(new SaveUserTransaction(users));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete(String userName)
    {
        try
        {
            prevayler.execute(new DeleteUserTransaction(userName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void list()
    {
        UserList list = (UserList) prevayler.prevalentSystem();
        list.print();
        System.out.println("Listing generated through DAO");
    }

    public String getNext()
    {
        UserList list = (UserList) prevayler.prevalentSystem();
        ListOrderedMap listOrderedMap = list.getUsers();
        if (!listOrderedMap.isEmpty())
            return (String) listOrderedMap.get(0);

        return null;
    }

    public ListOrderedMap retrieve()
    {
        UserList list = (UserList) prevayler.prevalentSystem();
        return list.getUsers();
    }

    /**
     * @return the list
     */
    public UserList getList()
    {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(UserList list)
    {
        this.list = list;
    }
}
