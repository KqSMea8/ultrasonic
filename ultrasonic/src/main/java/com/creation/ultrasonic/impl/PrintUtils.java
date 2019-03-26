package com.creation.ultrasonic.impl;

import android.text.TextUtils;
import android.util.Log;

import com.creation.ultrasonic.IDoctor;

import java.io.PrintWriter;
import java.io.StringWriter;

public class PrintUtils {
    public static String getStackClass(String probeClassName, String className, IDoctor.IPrintFilter printFilter) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        new Exception().printStackTrace(writer);
        String string = stringWriter.toString();
        return findClassStackString(probeClassName, className, string, printFilter);
    }

    private static String findClassStackString(String probeClassName, String className, String stackString, IDoctor.IPrintFilter printFilter) {
        // 去掉此类的打印
        stackString = deleteLines(stackString, PrintUtils.class.getName());
        if (stackString == null) {
            return null;
        }

        // 去掉hook的打印
        stackString = deleteLines(stackString, probeClassName);
        if (stackString == null) {
            return null;
        }

        //去掉前边类中的打印
        stackString = deleteLines(stackString, className);
        if (stackString == null) {
            return null;
        }

        int index = printFilter.indexOf(stackString);
        if (index >= 0) {
            //包含自己的类
            stackString = stackString.substring(index);
            index = stackString.indexOf("\n\t");
            if (index < 0) {
                return null;
            }
            stackString = stackString.substring(0, index);
        } else {
            //获取系统的类
            String[] strings = stackString.split("\n\t");
            for (String string : strings) {
                if (!TextUtils.isEmpty(string)) {
                    stackString = string;
                    break;
                }
            }
        }
        return stackString;
    }

    private static String deleteLines(String stackString, String string) {
        int index = stackString.lastIndexOf(string);
        if (index >= 0) {
            stackString = stackString.substring(index);
            index = stackString.indexOf("\n\t");
            if (index < 0 || stackString.length() <= 2) {
                return null;
            }
            stackString = stackString.substring(index + 2);
            return stackString;
        }
        return stackString;
    }
}
