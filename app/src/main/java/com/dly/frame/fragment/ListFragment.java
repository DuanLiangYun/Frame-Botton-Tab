package com.dly.frame.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dly.frame.R;

/**
 * 墓地列表Fragment
 */
public class ListFragment extends Fragment {
    /**
     * 根布局
     */
    private View rootView;

    /**
     * 单例实例方法
     */
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * 绑定根布局
         */
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
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
