package com.dly.frame.ui;

import android.os.Bundle;

import com.dly.frame.Config;
import com.dly.frame.R;
import com.dly.frame.fragment.LeftDrawerFragment;
import com.dly.frame.framework.MainFragmentInterface;
import com.dly.frame.frame_dly_main.FrameMainActivity;
import com.xuexiang.xui.XUI;

/**
 * 项目主页面
 */
public class IndexActivity extends FrameMainActivity implements MainFragmentInterface{
    /**
     * 页面加载入口
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 初始化XUI的主体
         */
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        /**
         * 初始化项目框架
         */
        super.initView(
                Config.fragments,//项目页面Fragment列表
                getResources().getStringArray(R.array.mainTabArr),//项目底部页面切换按钮文字数组
                Config.tabMainSelectorArr,//项目底部页面切换按钮selector列表
                Config.showFragmentIndex,//项目框架默认显示的页面下标
                getResources().getStringArray(R.array.titleArr));//项目标题栏文字数组
        /**
         * 设置不能左右滑动切换页面
         */
        super.setCanScroll(true);
        /**
         * 设置项目主页面左上角的文字
         */
        super.setLeftTitleText(R.string.app_name);
        /**
         * 设置项目是否开启左边侧滑页面功能
         */
        super.initDrawer(LeftDrawerFragment.newInstance());
        /**
         * 设置项目是否开启左边侧滑页面功能
         */
        super.setDrawerLockMode(true);
    }

    @Override
    public void onOpenDrawer() {
        super.openLeftDrawer();
    }

}
