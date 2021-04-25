package com.imyeego.mozart;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @authur : liuzhao
 * @time : 1/27/21 11:25 AM
 * @Des :
 */
public class Person extends BaseObservable {
    private String name;
    private int age;
    private String gender;


    public Person() {}


    @Inject
    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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

}
