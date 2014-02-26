package com.mkyong.customer.services;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.mkyong.aop.HijackAroundMethod;
import com.mkyong.aop.OsgiProxyFactory;
import com.mkyong.aop.SpringAopOsgiProxyFactory;

public class ServiceActivator implements BundleActivator {

    public void start(BundleContext context) {
        System.out.println("Starting the bundle");
        
		OsgiProxyFactory proxyFactory = (OsgiProxyFactory) new SpringAopOsgiProxyFactory();
		
		HijackAroundMethod hijack = new HijackAroundMethod();
		
		ICustomerService cust = (ICustomerService) proxyFactory.createProxy(
			new CustomerService(),
			hijack,
			ICustomerService.class
		);

		System.out.println("*************************");
		cust.printName();
		System.out.println("*************************");
		cust.printURL();
		System.out.println("*************************");
		try {
			cust.printThrowException();
		} catch (Exception e) {

		}
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle");
    }

}