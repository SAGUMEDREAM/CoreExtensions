package com.KafuuChino0722.coreextensions.core.fabricapi;


import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;

import java.lang.reflect.Field;

public class refect {
    public static class Block{
        public static void set(String classDevImport, String classImImport,
                               String DevField, String ImField,
                               Object Value, String Target,
                               String namespace, String id) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
            Field field;
            Class<?> className = Class.forName(classDevImport);
            Class<?> classIm = Class.forName(classImImport);
            Class<?> classTarget = Class.forName(Target);
            net.minecraft.block.Block target = IdentifierManager.getBlock(namespace,id);
            try {
                field = className.getDeclaredField(DevField);
            } catch (NoSuchFieldException e) {
                field = classIm.getDeclaredField(ImField);
            }
            field.setAccessible(true);
            field.set(target, Value);
        }
    }
    public static class Item{
        public static void set(String classDevImport, String classImImport,
                               String DevField, String ImField,
                               Object Value, String Target,
                               String namespace, String id) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
            Field field;
            Class<?> className = Class.forName(classDevImport);
            Class<?> classIm = Class.forName(classImImport);
            Class<?> classTarget = Class.forName(Target);
            net.minecraft.item.Item target = IdentifierManager.getItem(namespace,id);
            try {
                field = className.getDeclaredField(DevField);
            } catch (NoSuchFieldException e) {
                field = classIm.getDeclaredField(ImField);
            }
            field.setAccessible(true);
            field.set(target, Value);
        }
    }
}
