package com.mkyong.aop;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.aop.Advice;
import org.springframework.aop.SpringProxy;
import org.springframework.aop.framework.ProxyFactory;

public class SpringAopOsgiProxyFactory implements OsgiProxyFactory {

        @Override
        public Object createProxy(Object target, Advice advice, Class<?>... interfaces) {
                List<Advice> advices = new ArrayList<Advice>();
                advices.add(advice);
                return createProxy(target, advices, interfaces);
        }

        @Override
        public Object createProxy(Object target, List<Advice> advices,
                        Class<?>... interfaces) {
                if(interfaces == null || interfaces.length == 0) {
                        throw new IllegalArgumentException("at least one interface is required");
                }
                ProxyFactory proxyFactory = new ProxyFactory(target);
                for(Advice advice : advices) {
                        proxyFactory.addAdvice(advice);
                }
                ChainedClassloader loader = new ChainedClassloader();
                for(Class<?> itface : interfaces) {
                        proxyFactory.addInterface(itface);
                        loader.addLoader(itface.getClassLoader());
                }
                loader.addLoader(SpringProxy.class.getClassLoader());           
                return proxyFactory.getProxy(loader);
        }

}
