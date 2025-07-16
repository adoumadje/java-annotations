package com.example.basics.annotation;

import java.util.ArrayList;

public class GeneralPurpose extends Parent {
    @Override
    public void method1() {
        System.out.println("method1 inside child");
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        int a = 10;

        GeneralPurpose general = new GeneralPurpose();
        general.method1();

        @SuppressWarnings({"rawtypes", "unused"})
        ArrayList arrayList = new ArrayList<>();

//        Integer myInt = new Integer(1);
    }
}


class Parent {
    public void method1() {
        System.out.println("method1 inside Parent");
    }
}

