package com.beckproduct.prevayler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.collections.map.ListOrderedMap;

import com.beckproduct.prevayler.repository.UserRepository;

public class IndexUsers
{

    public IndexUsers()
    {
    }

    public void run(String fileName)
    {
        try
        {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + fileName);
            Statement stmt = conn.createStatement();
            String sqlQuery = "SELECT EBK_USERNAME FROM lead ";
            ResultSet results = stmt.executeQuery(sqlQuery);

            save(results);
            results.close();
            stmt.close();
            conn.close();

            list();
        }
        catch (Exception e)
        {
            System.out.println("Oops-> " + e);
        }
    }

    private void save(ResultSet results) throws Exception
    {
        String userName;
        ListOrderedMap userList = new ListOrderedMap();

        while (results.next())
        {
            userName = results.getString("EBK_USERNAME");
            System.out.println("EBK_USERNAME= " + userName);
            userList.put(userName, "Y");
        }
        UserRepository userRepository = new UserRepository();
        userRepository.save(userList);
    }
    
    private void list()
    {
        UserRepository userRepository = new UserRepository();
        userRepository.list();
    }

    public static void main(String[] args)
    {
        IndexUsers indexUsers = new IndexUsers();
        indexUsers.run(args[0]);
    }
}
