package com.movie.vo;

import java.util.List;

public class User {
	private long userId;
	private String name;
	private String userName;
	private String password;
	List<Long> listOfMoviesLikedByTheUser;
	List<Long> listOfMoviesDisLikedByTheUser;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setListOfMoviesDisLikedByTheUser(List<Long> listOfMoviesDisLikedByTheUser) {
		this.listOfMoviesDisLikedByTheUser = listOfMoviesDisLikedByTheUser;
	}
	public List<Long> getListOfMoviesLikedByTheUser() {
		return listOfMoviesLikedByTheUser;
	}
	public void setListOfMoviesLikedByTheUser(List<Long> listOfMoviesLikedByTheUser) {
		this.listOfMoviesLikedByTheUser = listOfMoviesLikedByTheUser;
	}
	public List<Long> getListOfMoviesDisLikedByTheUser() {
		return listOfMoviesDisLikedByTheUser;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", userName=" + userName + ", password=" + password + ", listOfMoviesLikedByTheUser="
			+ listOfMoviesLikedByTheUser + ", listOfMoviesDisLikedByTheUser=" + listOfMoviesDisLikedByTheUser + "]";
	}
	
}
