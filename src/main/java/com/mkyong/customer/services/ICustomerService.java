package com.mkyong.customer.services;

public interface ICustomerService {

	public void setName(String name);

	public void setUrl(String url) ;

	public void printName();

	public void printURL();

	public void printThrowException();
}
