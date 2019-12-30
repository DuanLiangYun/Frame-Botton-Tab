package com.dly.frame.util;

import android.content.Context;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 屏幕帮助类
 */
public class ScreenUtils {
	private static int statusBarHeight;
	/**
	 * 获取屏幕宽度
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context) {
		return ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
				.getWidth();
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Context context) {
		return ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
				.getHeight();
	}

	/**
	 * 用于获取状态栏的高度。
	 *
	 * @return 返回状态栏高度的像素值。
	 */
	public static int getStatusBarHeight(Context context) {
		if (statusBarHeight == 0) {
			try {
				Class<?> c = Class.forName("com.android.internal.R$dimen");
				Object o = c.newInstance();
				Field field = c.getField("status_bar_height");
				int x = (Integer) field.get(o);
				statusBarHeight = context.getResources().getDimensionPixelSize(x);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return statusBarHeight;
	}

	/**
	 * dp转换成px
	 */
	public static float dp2px(Context context, float dpValue){
		float scale=context.getResources().getDisplayMetrics().density;
		return (dpValue*scale+0.5f);
	}

	/**
	 * px转换成dp
	 */
	public static float px2dp(Context context, float pxValue){
		float scale=context.getResources().getDisplayMetrics().density;
		return (pxValue/scale+0.5f);
	}
	/**
	 * sp转换成px
	 */
	public static float sp2px(Context context, float spValue){
		float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
		return (spValue*fontScale+0.5f);
	}
	/**
	 * px转换成sp
	 */
	public static float px2sp(Context context, float pxValue){
		float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
		return (pxValue/fontScale+0.5f);
	}
}
