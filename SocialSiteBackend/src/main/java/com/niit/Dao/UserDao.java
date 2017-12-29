package com.niit.Dao;

import com.niit.Model.User;

public interface UserDao
{
	void registerUser(User user);
	boolean isEmailValid(String email);
	boolean isUsernameValid(String username);
	User login(User user);
	void updateUser(User user);
	User getUserByUsername(String username);

}