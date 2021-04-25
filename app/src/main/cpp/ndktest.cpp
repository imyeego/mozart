//
// Created by mac on 3/22/21.
//
#include <jni.h>
#include <stdio.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_imyeego_mozart_NdkTest_fromNdk(JNIEnv *env, jclass clazz) {
    // TODO: implement fromNdk()
    return env->NewStringUTF("From C");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_imyeego_mozart_NdkTest_fromC(JNIEnv *env, jclass clazz, jobject object) {
    jclass clz = env->FindClass("com/imyeego/mozart/Person");

    jmethodID jmethodId = env->GetMethodID(clz, "getName", "()Ljava/lang/String;");

    return static_cast<jstring>(env->CallObjectMethod(object, jmethodId));
}
