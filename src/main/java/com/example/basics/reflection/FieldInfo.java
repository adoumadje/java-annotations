package com.example.basics.reflection;

import java.lang.reflect.Field;

public class FieldInfo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Entity e = new Entity(18, "tt");
        Class<? extends Entity> clss = e.getClass();

        Field[] allPubFields = clss.getFields();
//        for (Field f: allPubFields) {
//            System.out.println(f.getName());
//        }

        Field[] declaredFields = clss.getDeclaredFields();
//        for(Field field: declaredFields) {
//            System.out.println(field.getName());
//        }

        Field type = clss.getField("type");
        System.out.println(type);
        type.set(e, "Caleb");

        Field value = clss.getDeclaredField("value");
        System.out.println(value);
        value.setAccessible(true);
        value.set(e, 18);

        System.out.println(e);
    }
}

