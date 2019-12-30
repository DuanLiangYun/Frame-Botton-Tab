package com.dly.frame.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目程序工具类
 */
public class AppUtils {
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

    /**
     * 判断字符串是否为空
     * @param text  目标字符串
     */
    public static boolean isEmpty(String text){
        return text == null || text.equals("");
    }

    /**
     * String类型的List转换成String字符串
     * @param list  需要转换的list
     * @param separator  分隔符
     * @return  转换后的字符串
     */
    public static String list2String(List<String> list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否为URL
     * @param urls 需要判断的String类型url
     * @return true:是URL；false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        boolean isurl = false;
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";//设置正则表达式

        Pattern pat = Pattern.compile(regex.trim());//对比
        Matcher mat = pat.matcher(urls.trim());
        isurl = mat.matches();//判断是否匹配
        if (isurl) {
            isurl = true;
        }
        return isurl;
    }


    /**
     * 判断服务是否开启
     *
     * @return
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        if (TextUtils.isEmpty(ServiceName)) {
            return false;
        }
        ActivityManager myManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager
                .getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName()
                    .equals(ServiceName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取APP版本名
     * @param ctx
     * @return
     */
    public static String getVersionName(Context ctx) {
        // 获取packagemanager的实例
        String version = "1.0.0";
        try {
            PackageManager packageManager = ctx.getPackageManager();
            //getPackageName()是你当前程序的包名
            PackageInfo packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            version = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取APP版本号
     * @param ctx
     * @return
     */
    public static int getVersionCode(Context ctx) {
        // 获取packagemanager的实例
        int version = 0;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            //getPackageName()是你当前程序的包名
            PackageInfo packInfo = packageManager.getPackageInfo(ctx.getPackageName(), 0);
            version = packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }
}
