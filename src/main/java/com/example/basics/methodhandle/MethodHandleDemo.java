package com.example.basics.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandleDemo {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        Class<?> clazz = lookup.findClass(Student.class.getName());

        MethodType getterType = MethodType.methodType(String.class);
        Student student = new Student();
        student.setCourse("JAVA");
        MethodHandle getCourseMethodhandle = lookup.findVirtual(Student.class, "getCourse", getterType);
        System.out.println(getCourseMethodhandle.invoke(student));

        MethodType type = MethodType.methodType(void.class);
        MethodHandle noArgsConst = lookup.findConstructor(Student.class, type);
        Student unknown = (Student) noArgsConst.invoke();
        System.out.println(unknown);

        MethodType type1 = MethodType.methodType(void.class, String.class, String.class);
        MethodHandle paramConst = lookup.findConstructor(Student.class, type1);
        Student bellingham = (Student) paramConst.invoke("Bellingham", "Computer Architecture");
        System.out.println(bellingham);

        MethodType setterType = MethodType.methodType(void.class, String.class);
        MethodHandle setNameHandle = lookup.findVirtual(clazz, "setName", setterType);
        setNameHandle.invoke(unknown, "Vinicius");
        System.out.println(unknown);

        MethodType staticType = MethodType.methodType(void.class, int.class);
        MethodHandle setNumOfStudenthandle = lookup.findStatic(clazz, "setNumOfStudent", staticType);
        setNumOfStudenthandle.invoke(10);
        System.out.println("Number of student = " + Student.numOfStudent);

//        MethodHandle nameGetter = lookup.findGetter(clazz, "name", String.class);
//        System.out.println(nameGetter.invoke(bellingham));
//        MethodHandle nameSetter = lookup.findSetter(clazz, "name", String.class);
//        nameSetter.invoke(unknown, "Mbappe");
//        System.out.println(unknown);

        MethodHandles.Lookup privateLookup = MethodHandles.privateLookupIn(Student.class, lookup);

        MethodHandle namePrivateGetter = privateLookup.findGetter(clazz, "name", String.class);
        System.out.println(namePrivateGetter.invoke(bellingham));
        MethodHandle namePrivateSetter = privateLookup.findSetter(clazz, "name", String.class);
        namePrivateSetter.invoke(unknown, "Mbappe");
        System.out.println(unknown);

        // VarHandle
        VarHandle courseVarHandle = privateLookup.findVarHandle(clazz, "course", String.class);
        courseVarHandle.set(unknown, "Data Structures");
        System.out.println(unknown);
        System.out.println(courseVarHandle.get(bellingham));
    }
}
