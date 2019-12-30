package com.dly.frame;

import android.Manifest;
import android.support.v4.app.Fragment;

import com.dly.frame.fragment.ListFragment;
import com.dly.frame.fragment.MapFragment;
import com.dly.frame.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目配置文件
 */
public class Config {
    /**
     * 项目需要动态获取的权限组
     */
    public static String[] requestedPermissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,  //写入权限
            Manifest.permission.READ_PHONE_STATE,  //读取权限
            Manifest.permission.ACCESS_FINE_LOCATION   //GPS定位权限
    };

    /**
     * 主页面页面切换底部按钮的图标selector列表
     */
    public static List<Integer> tabMainSelectorArr = new ArrayList<Integer>() {{
        add(R.drawable.selector_tab_main_map);//地图selector
        add(R.drawable.selector_tab_main_user);//列表selector
        add(R.drawable.selector_tab_main_list);//个人中心selector
    }};
    /**
     * 项目主要页面Fragment
     */
    public static List<Fragment> fragments = new ArrayList<Fragment>() {{
        add(MapFragment.newInstance());//地图Fragment
        add(ListFragment.newInstance());//详情列表Fragment
        add(UserFragment.newInstance());//个人中心Fragment
    }};
    /**
     * 项目框架默认显示的页面下标
     */
    public static int showFragmentIndex = 0;
}
