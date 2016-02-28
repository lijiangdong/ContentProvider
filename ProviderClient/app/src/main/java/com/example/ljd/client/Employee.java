package com.example.ljd.client;

/**
 * Created by ljd-PC on 2016/2/28.
 */
public class Employee {
    private int id;
    private String workNum;
    private String name;
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    @Override
    public String toString(){
        return String.format("workNum:%s, name:%s, department:%s",
                getWorkNum(),getName(),getDepartment());
    }
}
