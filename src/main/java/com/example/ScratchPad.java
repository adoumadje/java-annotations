package com.example;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScratchPad {
    public static void main(String[] args) {
//        System.getProperties()
//                .forEach((k, v) -> {
//                    if(k.toString().startsWith("user")) {
//                        System.out.printf("%s --> %s%n", k, v);
//                    }
//                });

        String value = "com.example.practice.spring";
        String pkg = value.replace(".", "\\");
        Path path = Paths.get(System.getProperty("user.dir"), "src\\main\\java", pkg);

        System.out.println(path);

        File source = new File(path.toString());

        File[] files = findClassFiles(source);

        for(File file: files) {
            System.out.println(file.getName() + " ######### " + file.getPath());
        }
    }

    private static File[] findClassFiles(File source) {
        if(!source.isDirectory()) {
            throw new RuntimeException("Incorrect path");
        } else {
            return source.listFiles();
        }
    }
}
