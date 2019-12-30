package com.dly.frame.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dly.frame.R;

public class MapFragment extends Fragment {
    /**
     * 根布局
     */
    private View rootView;
    /**
     * 实现页面的单例
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Fragment页面加载（入口）
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * 绑定布局文件
         */
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        /**
         * 调用初始化页面控件方法
         */
        initUI();
        /**
         * 调用监听器设置方法
         */
        initListener();
        /**
         * 返回根布局
         */
        return rootView;
    }

    /**
     * 初始化页面控件方法
     */
    private void initUI() {

    }

    /**
     * 监听器设置方法
     */
    private void initListener(){

    }
}
