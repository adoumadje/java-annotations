package com.example.basics.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierInfo {
    public static void main(String[] args) throws NoSuchMethodException {
        Entity e = new Entity(12, "toto");
        Class<? extends Entity> clss = e.getClass();

        int classModifiers = clss.getModifiers();
        System.out.println("public: " + Modifier.isPublic(classModifiers)
                + ", interface: " + Modifier.isInterface(classModifiers));

        Method method = clss.getDeclaredMethod("method", null);
        int methodModifiers = method.getModifiers();
        System.out.println("private: " + Modifier.isPrivate(methodModifiers)
                + ", static: " + Modifier.isStatic(methodModifiers));
    }
}
