package com.xdclass.jvm;

public class A {


    static{
        System.out.println("enter static method");
    }

    private int age;

    private String name;

    private boolean isMan;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("enter set age");
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    @Override
    public String toString() {
        return "A{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", isMan=" + isMan +
                '}';
    }

    public A() {
        System.out.println("enter method");
    }

    public static void main(String[] args) {
         User user = new User();
         user.getId();
    }
}
