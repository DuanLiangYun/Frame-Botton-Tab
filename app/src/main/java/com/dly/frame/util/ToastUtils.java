package com.dly.frame.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 项目程序工具类
 */
public class ToastUtils {
    /**
     * 打印字符串
     * @param context  上下文
     * @param text  需要打印的字符串
     */
    public static void showToast(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
    /**
     * 打印字符串
     * @param context  上下文
     * @param text  需要打印的字符串
     */
    public static void showToast(Context context, boolean text){
        Toast.makeText(context,String.valueOf(text),Toast.LENGTH_LONG).show();
    }
    /**
     * 打印字符串
     * @param context  上下文
     * @param text  需要打印的字符串
     * @param duration 需要显示的时长:单位 ms(毫秒)
     */
    public static void showToast(Context context, String text, int duration){
        Toast.makeText(context,text,duration).show();
    }
    /**
     * 打印字符串
     * @param context  上下文
     * @param num  需要打印的数字
     */
    public static void showToast(Context context, int num){
        Toast.makeText(context,String.valueOf(num),Toast.LENGTH_LONG).show();
    }
    /**
     * 打印字符串
     * @param context  上下文
     * @param num  需要打印的数字
     * @param duration 需要显示的时长:单位 ms(毫秒)
     */
    public static void showToast(Context context, int num, int duration){
        Toast.makeText(context,String.valueOf(num),duration).show();
    }
}
