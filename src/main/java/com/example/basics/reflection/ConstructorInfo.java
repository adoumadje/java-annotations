package com.example.basics.reflection;

import java.lang.reflect.Constructor;

public class ConstructorInfo {
    public static void main(String[] args) throws Exception {
        Class<?> clss = Class.forName("com.example.basics.reflection.Entity");

        Constructor<?>[] pbConstructors = clss.getConstructors();
//        for(Constructor<?> constructor: pbConstructors) {
//            System.out.println(constructor);
//        }

        System.out.println("-----------------");

        Constructor<?>[] dcConstructors = clss.getDeclaredConstructors();
//        for(Constructor<?> constructor: dcConstructors) {
//            System.out.println(constructor);
//        }

        Constructor<?> pbConstructor = clss.getConstructor(int.class, String.class);
        Entity e = (Entity) pbConstructor.newInstance(15, "caleb");
        System.out.println(e);

        Constructor<?> pvConstructor = clss.getDeclaredConstructor(null);
        pvConstructor.setAccessible(true);
        Entity e1 = (Entity) pvConstructor.newInstance(null);
        System.out.println(e1);
    }
}
