package com.example.basics.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReadingAnnotation {
    public static void main(String[] args) throws Exception {
        Class<?> clss = Class.forName("com.example.basics.annotation.UtilityClass");
        Constructor<?> constructor = clss.getConstructor();

        UtilityClass util = (UtilityClass) constructor.newInstance();

        Method[] methods = clss.getMethods();

        for(Method method: methods) {
            if(method.isAnnotationPresent(MostUsed.class)) {
                MostUsed annotation = method.getAnnotation(MostUsed.class);
                String value = annotation.value();
                method.invoke(util, value.toUpperCase());
            }
        }
    }
}
