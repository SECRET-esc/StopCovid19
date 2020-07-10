//
// Created by Max Kazanovsky on 10.07.2020.
//
#include <string>
#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_covid19app_MainActivity_hello(
        JNIEnv* env,
        jobject obj) {
std::string msg = "This is my app!!!!!!!!!!!";
return env->NewStringUTF(msg.c_str());
}

