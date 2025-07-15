package com.example.basics.reflection;

import java.lang.reflect.Method;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        Entity e = new Entity(22, "tt");
        Class<? extends Entity> clss = e.getClass();

        Method[] allPublicMethods = clss.getMethods();

//        for(Method pm: allPublicMethods) {
//            System.out.println(pm.getName());
//        }

        System.out.println("-------");

        Method[] declaredMethods = clss.getDeclaredMethods();

//        for (Method dm: declaredMethods) {
//            System.out.println(dm.getName());
//        }

        Method setValue = clss.getDeclaredMethod("setValue", int.class);
        setValue.setAccessible(true);
        setValue.invoke(e, 10);

        Method getvalue = clss.getMethod("getValue", null);
        System.out.println(getvalue.invoke(e, null));
    }
}
