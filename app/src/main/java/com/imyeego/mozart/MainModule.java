package com.imyeego.mozart;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @authur : liuzhao
 * @time : 1/25/21 4:28 PM
 * @Des :
 */
@Module
public class MainModule {

    @Provides
    public Student provideStudent() {
        return new Student("liuzhao", 18, "男");
    }

    @Provides
    @Named("1")
    public Student provideStudent1() {
        return new Student("bingbing", 22, "女");
    }

    @Provides
    @Singleton
    public Person providePerson() {
        return new Person("liuzhao", 18, "male");
    }
}
