package com.example.basics.annotation;

public class Enhancement {

    public static void main(String[] args) {
        Box<String> box = new @NonEmpty @ReadOnly Box<>(1, "container");
        box.new @ReadOnly NestedBox(12, "cylinder");
    }

}
