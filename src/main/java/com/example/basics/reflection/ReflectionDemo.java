package com.example.basics.reflection;

import java.lang.reflect.Constructor;

public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        // MyClass myObj = new MyClass();
        Class<?> clss = Class.forName("com.example.basics.reflection.MyClass");
        Constructor<?> constructor  = clss.getDeclaredConstructor();
        constructor.setAccessible(true);
        MyClass newInstance = (MyClass) constructor.newInstance();
    }
}

class MyClass {
    private MyClass() {
        System.out.println("new object being created...");
    }
}
