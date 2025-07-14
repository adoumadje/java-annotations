package com.example.basics.annotations;

import java.io.Serializable;

public class GettingClassObject {
    public static void main(String[] args) throws Exception {
        Class<?> clss1 = Class.forName("java.lang.String");
        Class<?> clss2 = Class.forName("java.lang.String");
        System.out.println(clss1 == clss2);

        Class<?> clss3 = int.class;
        Class<?> clss4 = String.class;
        System.out.println(clss3);
        System.out.println(clss4);

        Person person = new Person();
        Class<?> clss5 = person.getClass();
        System.out.println(clss5);

        Class<?> superClass = clss5.getSuperclass();
        System.out.println(superClass);

        Class<?>[] interfaces = clss5.getInterfaces();
        for(Class<?> interf: interfaces) {
            System.out.println(interf);
        }
    }
}

class Person implements Serializable, Cloneable {

}
