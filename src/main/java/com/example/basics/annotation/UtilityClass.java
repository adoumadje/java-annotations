package com.example.basics.annotation;

@MostUsed
public class UtilityClass {
    public void doStuff() {
        System.out.println("without param");
    }

    @MostUsed
    public void doStuff(String s) {
        System.out.println("with String param: " + s);
    }

    public void doStuff(int i) {
        System.out.println("with int param: " + i);
    }
}

class SubUtil extends UtilityClass {

}
