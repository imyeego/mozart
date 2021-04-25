package com.imyeego.mozart;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @authur : liuzhao
 * @time : 1/25/21 3:50 PM
 * @Des :
 */
@Component(modules = MainModule.class)
@Singleton
public interface MainComponent {
    void injectFrom(MainActivity activity);
}
