package com.example.basics.annotation;

public class Box<@NonEmpty @ReadOnly T> {
    @NonEmpty int size;
    T type;

    public Box(int size, T type) {
        this.size = size;
        this.type = type;
    }

    public class NestedBox extends Box<T> {

        public NestedBox(int size, @NonEmpty T type) {
            super(size, type);
        }
    }
}
