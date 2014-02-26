package com.mkyong.aop;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author acogoluegnes
 *
 */
public class ChainedClassloader extends ClassLoader {
        
        private Set<ClassLoader> loaders = new HashSet<ClassLoader>();

    public void addLoader(ClassLoader loader) {
        loaders.add(loader);
    }

    public Class<?> findClass(String name) throws ClassNotFoundException {
        for (ClassLoader loader : loaders) {
            try {
                return loader.loadClass(name);
            } catch (ClassNotFoundException cnfe) {

            }
        }
        throw new ClassNotFoundException(name);
    }
    
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        for (ClassLoader loader : loaders) {
            try {
                return loader.loadClass(name);
            } catch (ClassNotFoundException cnfe) {
                
            }
        }
        throw new ClassNotFoundException(name);
    }

    public URL getResource(String name) {
        for (ClassLoader loader : loaders) {
            URL url = loader.getResource(name);
            if (url != null)
                return url;
        }
        return null;
    }
}
