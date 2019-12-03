package com.markopavlenko.usermanagement.db;

import java.util.Collection;

import com.markopavlenko.usermanagement.User;

import java.util.Collection;

public interface UserDao {

    User create(User user) throws DatabaseException;

    void update(User user) throws DatabaseException;

    void delete(User user) throws DatabaseException;

    User find(Long ID) throws DatabaseException;

    Collection <User> findAll() throws DatabaseException;

    void setConnectionFactory(ConnectionFactory connectionFactory);

    Collection<User> find(String firstName, String lastName) throws DatabaseException;

	User find(long id) throws DatabaseException;
}
