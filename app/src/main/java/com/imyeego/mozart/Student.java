package com.imyeego.mozart;

import javax.inject.Inject;

/**
 * @authur : liuzhao
 * @time : 1/25/21 3:42 PM
 * @Des :
 */
public class Student {

    private String name;
    private int age;
    private String gender;

    public Student(){}

    @Inject
    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
