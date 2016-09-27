package com.ironyard.services;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by sam on 9/27/16.
 */
public class Database {

    public void init() throws SQLException {
        Server.createWebServer().start();
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./main", "sa", null);

    }


    public void createAllData() throws SQLException{
        Statement stmt = getConnection().createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS user (user_id IDENTITY, user_firstname VARCHAR NOT NULL, user_lastname VARCHAR NOT NULL, user_email VARCHAR NOT NULL, user_login VARCHAR NOT NULL, user_password VARCHAR NOT NULL)");
        stmt.execute("CREATE TABLE IF NOT EXISTS blog (" +
                "blog_id IDENTITY, " +
                "blog_title VARCHAR, " +
                "blog_creationDate INT, " +
                "blog_user_id INT, " +
                " FOREIGN KEY ( blog_user_id) " +
                "    REFERENCES user(user_id)) ");

        stmt.execute("CREATE TABLE IF NOT EXISTS posts (" +
                "posts_id IDENTITY, " +
                "posts_title VARCHAR, " +
                "posts_text VARCHAR, " +
                "posts_creationDate INT, " +
                "posts_blog_id INT, " +
                " FOREIGN KEY ( posts_blog_id) " +
                "    REFERENCES blog(blog_id)) ");

        stmt.execute("CREATE TABLE IF NOT EXISTS comment (" +
                "comment_id IDENTITY, " +
                "comment_username VARCHAR, " +
                "comment_text VARCHAR, " +
                "comment_posts_id INT, " +
                " FOREIGN KEY ( comment_posts_id) " +
                "    REFERENCES posts(posts_id)) ");

    }

}
