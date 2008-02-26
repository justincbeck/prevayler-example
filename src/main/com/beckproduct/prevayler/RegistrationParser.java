package com.beckproduct.prevayler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class RegistrationParser
{
    private String userName;

    private String password;

    public RegistrationParser(String fileName)
    {
        String index = findIndex();
        System.out.println("Index found : " + index);
        try
        {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            Connection conn = DriverManager.getConnection("jdbc:relique:csv:" + fileName);
            Statement stmt = conn.createStatement();
            String sqlQuery = "SELECT EBK_USERNAME, EBK_PASSWORD FROM lead WHERE EBK_USERNAME =" + index;

            ResultSet results = stmt.executeQuery(sqlQuery);

            while (results.next())
            {
                userName = results.getString("EBK_USERNAME");
                password = results.getString("EBK_PASSWORD");
                System.out.println("EBK_USERNAME= " + userName + " EBK_PASSWORD= " + password);
            }
            // clean up
            results.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Oops-> " + e);
        }
    }

    public static void main(String[] args)
    {
        RegistrationParser rp = new RegistrationParser(args[0]);
        System.out.println("Username - " + rp.getUserName() + " Password - " + rp.getPassword());
    }

    private String findIndex()
    {
        UserDAO userDAO = new UserDAO();
        userDAO.list();
        String index = userDAO.getNext();
        userDAO.delete(index);
        System.out.println("AFTER UPDATE - HERE IS THE LIST : ");
        userDAO.list();

        return index;
    }

    public String getPassword()
    {
        return password;
    }

    public String getUserName()
    {
        return userName;
    }
}
