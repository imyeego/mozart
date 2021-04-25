package com.imyeego.mozart;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @authur : liuzhao
 * @time : 1/27/21 11:43 AM
 * @Des :
 */

@Component(modules = MainModule.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(SecondActivity scecondActivity);
}
