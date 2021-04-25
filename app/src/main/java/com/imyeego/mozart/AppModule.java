package com.imyeego.mozart;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @authur : liuzhao
 * @time : 1/27/21 11:46 AM
 * @Des :
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    public Person providePerson() {
        return new Person("liuzhao", 18, "male");
    }
}
