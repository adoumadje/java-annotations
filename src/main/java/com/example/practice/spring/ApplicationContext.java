package com.example.practice.spring;

import com.example.practice.spring.annotations.Component;
import com.example.practice.spring.annotations.ComponentScan;
import com.example.practice.spring.annotations.Configuration;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ApplicationContext {
    private static HashMap<Class<?>, Object> hashMap = new HashMap<>();

    public ApplicationContext(Class<?> appConfig) {
        initializeApplicationContext(appConfig);
    }

    private void initializeApplicationContext(Class<?> appConfig) {
        if(!appConfig.isAnnotationPresent(Configuration.class)) {
            throw new RuntimeException("AppConfig should have @Configuration annotation");
        }

        if(!appConfig.isAnnotationPresent(ComponentScan.class)) {
            throw new RuntimeException("AppConfig should be annotated with @ComponentScan");
        }

        ComponentScan componentScan = appConfig.getAnnotation(ComponentScan.class);
        String pkg = componentScan.value();
        Path path = Paths.get(System.getProperty("user.dir"), "src\\main\\java",
                pkg.replace(".", "\\"));
        File source = new File(path.toString());

        File[] files = findClassFiles(source);

        for(File file: files) {
            if(file.getName().contains(".java")) {
                try {
                    Class<?> clazz = Class.forName(pkg + "."
                            + file.getName().replace(".java", ""));
                    if(clazz.isAnnotationPresent(Component.class)) {
                        Object bean = createBean(clazz);
                        hashMap.put(clazz, bean);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Object createBean(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            Object bean = constructor.newInstance();
            return bean;
        } catch (NoSuchMethodException | InvocationTargetException
                 | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private File[] findClassFiles(File source) {
        if(!source.isDirectory()) {
            throw new RuntimeException("Incorrect path");
        } else {
            return source.listFiles();
        }
    }
}
