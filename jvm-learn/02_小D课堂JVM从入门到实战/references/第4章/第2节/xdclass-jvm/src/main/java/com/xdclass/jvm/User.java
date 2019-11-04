package com.xdclass.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Integer id;
    private String name;
    private Double cash;
    //此处省略getter/setter方法


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public User(Integer id, String name, Double cash) {
        this.id = id;
        this.name = name;
        this.cash = cash;
    }

    public User() {
    }

    public static void main(String[] args) {
        User u1 = new User(11,"22",33d);
        User u2 = new User(44,"55",66d);
        User u3 = new User(77,"88",99d);
        List<User> userList = new ArrayList<>();
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        List<User> userList1 = userList.stream().filter(user -> user.getId()>22).collect(Collectors.toList());
        System.out.println("111");
    }


}
