package com.beckproduct.prevayler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beckproduct.prevayler.domain.User;
import com.beckproduct.prevayler.repository.UserRepository;

public class UserRepositoryTest
{
    private Logger logger = Logger.getLogger(this.getClass());

    private UserRepository repository;

    private ResultSet results;

    @Before
    public void init() throws Exception
    {
        BasicConfigurator.configure();
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource dataSource = (DataSource) context.getBean("dataSource");
        try
        {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            String sqlQuery = "SELECT EBK_USERNAME, EBK_PASSWORD FROM lead ";
            results = stmt.executeQuery(sqlQuery);
        }
        catch (SQLException e)
        {
            logger.fatal(e.getMessage(), e);
        }

        repository = new UserRepository();
    }

    @Test
    public void testCreateAll() throws Exception
    {
        String username;
        String password;
        ListOrderedMap userList = new ListOrderedMap();

        while (results.next())
        {
            username = results.getString("EBK_USERNAME");
            password = results.getString("EBK_PASSWORD");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);

            userList.put(username, user);
        }

        repository.create(userList);
    }

    @Test
    public void testCreate() throws Exception
    {
        User user = new User();
        user.setUsername("name");
        user.setPassword("password");

        repository.create(user);
    }

    @Test
    public void testRead() throws Exception
    {
        String username;

        results.next();

        username = results.getString("EBK_USERNAME");

        User user = repository.read(username);

        Assert.assertNotNull(user);
    }

    @Test
    public void testUpdate() throws Exception
    {

    }

    @Test
    public void testDelete() throws Exception
    {
        repository.delete("jbeck");
    }
}
