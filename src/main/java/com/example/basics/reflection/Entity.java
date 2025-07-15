package com.example.basics.reflection;

public class Entity {
    private int value;
    public String type;

    public Entity(int val, String type) {
        this.value = val;
        this.type = type;
    }

    private Entity() {
        this.value = 18;
        this.type = "id";
    }

    public int getValue() {
        return value;
    }

    private void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private static void method() {
        // do something
    }

    @Override
    public String toString() {
        return "Entity{" +
                "value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}
