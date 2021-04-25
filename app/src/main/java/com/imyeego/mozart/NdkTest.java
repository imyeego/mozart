package com.imyeego.mozart;

/**
 * @authur : liuzhao
 * @time : 3/22/21 12:09 PM
 * @Des :
 */
public class NdkTest {

    static {
        System.loadLibrary("ndktest");
    }

    public native static String fromNdk();

    public native static String fromC(Person person);
}
