package com.dly.frame.frame_dly_main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.List;

public class FrameMainActivity extends AppCompatActivity {

    private MyViewPager mainPager;
    private RadioGroup menuRadioGroup;
    private TitleBar frameTitle;

    private FragmentPagerAdapter pagerAdapter;

    private DrawerLayout mainDrawerLayout;

    private FrameLayout left_drawer;

    private boolean isOpenDrawer = false;

    private String[] titleArr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_main);
        /**
         * 实例化页面控件
         */
        mainPager = findViewById(R.id.fragment_vp);

        menuRadioGroup = findViewById(R.id.tabs_rg);
        //侧边栏布局
        mainDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerLayout);
        //设置侧边栏默认关闭
        mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        left_drawer = (FrameLayout)findViewById(R.id.left_drawer);
        frameTitle = (TitleBar)findViewById(R.id.frame_title);
    }

    /**
     * 初始化底部导航菜单，并设置相关监听器
     *
     * @param mFragments   列表：需要显示的Fragment页面
     * @param mainTabArr     列表：需要显示的底部按钮文字
     * @param drawables    列表：底部按钮文字上方的图标
     * @param checkedIndex 整数：需要默认显示的页面和默认选中的按钮序号（从0开始计）
     * @param titleArr     字符串数组：用来设置主界面上的标题栏内容
     */
    @SuppressLint("NewApi")
    public void initView(List<Fragment> mFragments, String[] mainTabArr, List<Integer> drawables, int checkedIndex,String[] titleArr) {
        this.titleArr = titleArr;
        /**
         * 防止fragment切换页面时重新加载的页面数：与需要总的页面数相等
         */
        mainPager.setOffscreenPageLimit(mFragments.size());
        /**
         * 创建适配器
         */
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        /**
         * 设置适配器
         */
        mainPager.setAdapter(pagerAdapter);
        /**
         * 设置页面左右滑动的监听器
         */
        mainPager.addOnPageChangeListener(mPageChangeListener);

        /**
         * 根据传入是底部按钮数组初始化页面
         */
        for (int i = 0; i < mainTabArr.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            /**
             * 设置布局参数
             */
            RadioGroup.LayoutParams lparams = new RadioGroup.LayoutParams(0, RadioGroup.LayoutParams.MATCH_PARENT);
            /**
             * 均分父容器
             */
            lparams.weight = 1;
            /**
             * 设置到对应的控件上
             */
            radioButton.setLayoutParams(lparams);
            /**
             * 设置文字居中
             */
            radioButton.setGravity(Gravity.CENTER);
            /**
             * 这是按钮文字上方的图标对象
             */
            radioButton.setCompoundDrawablesRelativeWithIntrinsicBounds(null, getResources().getDrawable(drawables.get(i)), null, null);
            /**
             * 去掉单选框左边的选中图标
             */
            radioButton.setButtonDrawable(null);
            /**
             * 设置按钮边框
             */
            radioButton.setPadding(5, 5, 5, 5);
            /**
             * 设置文字
             */
            radioButton.setText(mainTabArr[i]);
            /**
             * 设置文字样式：大小、是否选中
             */
            radioButton.setTextAppearance(this, R.style.Custom_TabRadioButton);
            /**
             * 将单选按钮加到RadioGroup中
             */
            menuRadioGroup.addView(radioButton);
            /**
             * 设置默认选中项:该方法必须在addView之后调用，否则设置选中后不可修改
             */
            radioButton.setChecked(i == checkedIndex);
        }
        /**
         * 设置RadioGroup的改变监听器
         */
        menuRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
        /**
         * 显示对应的页面Pager
         */
        mainPager.setCurrentItem(checkedIndex);

        frameTitle.setTitle(titleArr[checkedIndex]);
    }

    /**
     * 页面关闭时调用：注销页面滑动监听器
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPager.removeOnPageChangeListener(mPageChangeListener);
    }

    /**
     * 页面左右滑动的监听器对象
     */
    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton radioButton = (RadioButton) menuRadioGroup.getChildAt(position);
            radioButton.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 底部RadioGroup选择组改变时的监听器对象
     */
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for (int i = 0; i < group.getChildCount(); i++) {
                if (group.getChildAt(i).getId() == checkedId) {
                    mainPager.setCurrentItem(i);
                    frameTitle.setTitle(titleArr[i]);
                    return;
                }
            }
        }
    };

    /**
     * 页面的数据适配器
     */
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments == null ? null : this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments == null ? 0 : this.fragments.size();
        }
    }

    /**
     * 设置左侧边栏是否开启：默认关闭
     * @param isOpen  是否开启：true开启，false关闭
     */
    public void setDrawerLockMode(boolean isOpen) {
        if (isOpen) {
            mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }


    /**
     * 打开坐标侧滑窗口
     */
    public void openLeftDrawer(){
        mainDrawerLayout.openDrawer(left_drawer);
        isOpenDrawer = true;
    }
    /**
     * 关闭坐标侧滑窗口
     */
    public void closeLeftDrawer(){
        mainDrawerLayout.closeDrawer(left_drawer);
        isOpenDrawer = false;
    }
    /**
     * 初始化侧滑窗口
     */
    public void initDrawer(Fragment fragment){
        FragmentManager fragmentManager;
        FragmentTransaction transaction;
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.left_drawer,fragment);
        transaction.commit();
    }

    /**
     * 设置主页面是否可以左右滑动来切换页面
     * @param canScroll  false：不可滑动；true：可滑动切换
     */
    public void setCanScroll(boolean canScroll){
        mainPager.setCanScroll(canScroll);
    }

    /**
     * 设置主页面的标题栏内容
     * @param title   标题栏文字内容
     */
    public void setTitleText(String title){
        frameTitle.setTitle(title);
    }
    /**
     * 设置主页面左边标题的标题栏内容(以字符串的形式设置)
     * @param leftTitle   标题栏文字内容
     */
    public void setLeftTitleText(String leftTitle){
        frameTitle.setLeftText(leftTitle);
    }
    /**
     * 设置主页面左边标题的标题栏内容(以文字id设置：传入的文字在string.xml文件中)
     * @param leftTitle   标题栏文字内容
     */
    public void setLeftTitleText(int leftTitle){
        frameTitle.setLeftText(leftTitle);
    }
    /**
     * 设置主页面左边图标
     * @param imageResource   标题栏文字内容
     */
    public void setleftImageResource(int imageResource){
        frameTitle.setLeftImageResource(imageResource);
    }

    @Override
    public boolean isDestroyed() {
        return false;
//        return super.isDestroyed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && isOpenDrawer){
            closeLeftDrawer();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
