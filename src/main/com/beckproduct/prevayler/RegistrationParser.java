package com.beckproduct.prevayler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.beckproduct.prevayler.repository.UserRepository;

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
        UserRepository userRepository = new UserRepository();
        userRepository.list();
        String index = userRepository.getNext();
        userRepository.delete(index);
        System.out.println("AFTER UPDATE - HERE IS THE LIST : ");
        userRepository.list();

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
