package com.KafuuChino0722.coreextensions.core.api;

public enum MethodMath {
    MoreThan,
    LessThan,
    MoreEquals,
    LessEquals,
    Equals;

    public static boolean getMathInt(String str,int b) {
        MethodMath mathSyc = null;

        int a = MethodMath.getStr2Int(str);

        if(str.contains("==")) {
            mathSyc = MethodMath.Equals;
        }
        else if(str.contains("<=")) {
            mathSyc = MethodMath.LessEquals;
        }
        else if(str.contains(">=")) {
            mathSyc = MethodMath.MoreEquals;
        }
        else if(str.contains(">")) {
            mathSyc = MethodMath.MoreThan;
        }
        else if(str.contains("<")) {
            mathSyc = MethodMath.LessThan;
        }

        if(mathSyc == Equals && a == b) {
            return false;
        }
        else if(mathSyc == LessEquals && a >= b) {
            return false;
        }
        else if(mathSyc == MoreEquals && a <= b) {
            return false;
        }
        else if(mathSyc == MoreThan && a > b) {
            return false;
        }
        else if(mathSyc == LessThan && a < b) {
            return false;
        }
        return true;
    }

    public static int getStr2Int(String str) {
        str = str.replaceAll("[^0-9]", "");
        return Integer.parseInt(str);
    }
}
