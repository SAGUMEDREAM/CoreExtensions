package com.KafuuChino0722.coreextensions;

import java.lang.reflect.Method;

public class Resources {
    public Class<?> clazz;

    public Resources(Class<?> clazz) {
        this.clazz = clazz;
        InvokeBootstrap();
    }

    public void InvokeBootstrap() {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getDeclaredMethod("bootstrap");
            method.invoke(instance);
        } catch (Exception e) {
            try {
                Method method = clazz.getDeclaredMethod("bootstrap");
                method.invoke(null);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }
}
