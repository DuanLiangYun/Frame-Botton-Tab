package com.dly.frame.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import com.dly.frame.Config;
import com.dly.frame.R;
import com.dly.frame.framework.PermissionInterface;
import com.dly.frame.fragment.PermissionHelper;
import com.dly.frame.util.AppUtils;

/**
 * 项目启动页面：主要用来获取项目相关权限
 */
public class MainActivity extends Activity implements PermissionInterface {
    /**
     * 定义动态获取权限的辅助类
     */
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView app_version= (TextView) findViewById(R.id.app_version);
        app_version.setText("当前版本:"+ AppUtils.getVersionName(this));

        /**
         * 实例化动态获取权限的辅助类
         */
        mPermissionHelper = new PermissionHelper(this, this);
        /**
         * 发起权限申请
         */
        mPermissionHelper.requestPermissions();
    }

    /**
     * 重写接口PermissionInterface的onRequestPermissionsResult方法
     * @param requestCode  请求码
     * @param permissions  权限列表
     * @param grantResults  请求结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 重写接口PermissionInterface的getPermissionsRequestCode方法
     */
    @Override
    public int getPermissionsRequestCode() {
        //设置权限请求requestCode，只有不跟onRequestPermissionsResult方法中的其他请求码冲突即可。
        return 10000;
    }

    /**
     * 重写接口PermissionInterface的getPermissions方法
     * @return  权限列表(字符串数组)
     */
    @Override
    public String[] getPermissions() {
        return Config.requestedPermissions;
    }

    /**
     * 重写接口PermissionInterface的requestPermissionsSuccess方法
     * 权限已全部被允许
     */
    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        initViews();
    }

    /**
     * 重写接口PermissionInterface的requestPermissionsFail方法
     * 权限未被全部允许
     */
    @Override
    public void requestPermissionsFail() {
        //权限请求不被用户允许。
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("权限管理");
        builder.setMessage("所需权限被拒绝，需要手动设置，是否进入手动设置权限？");
        builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", MainActivity.this.getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent,500);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        //显示AlertDialog对话框
        builder.show();
    }

    /**
     * 重写Activity的onActivityResult方法，用来获取设置界面跳转回来的的操作
     * @param requestCode  请求码：用于界面回传
     * @param resultCode  结果码：用于界面结果判断
     * @param data  数据：用于界面传值
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 500){
            /**
             * 当用户调到设置界面去对所需权限进行设置时，由于我们不好把控用户对所需权限的操作是否都是“允许”
             * 这时，我们只需要对权限再一次的去获取，如有不被的权限，程序会再次进行提示，
             * 直到所有权限都被执行方可结束权限获取操作
             */
            mPermissionHelper.requestPermissions();
        }else if (requestCode == 10) {

            Intent intent = new Intent(MainActivity.this, IndexActivity.class);

            MainActivity.this.startActivity(intent);
            finish();
        }
    }

    /**
     * 权限全部获取后的操作
     * 这里主要实现的是：界面跳转，界面跳转前会有2秒的休眠
     */
    private void initViews() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocationManager mLocationManager;
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS是否正常启动
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(MainActivity.this, "请打开GPS访问...", Toast.LENGTH_SHORT).show();
            // 返回开启GPS导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 10);
            return;
        }

        //已经拥有所需权限，可以放心操作任何东西了
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, IndexActivity.class);

                MainActivity.this.startActivity(intent);
                finish();
            }
        }, 1000);

    }

}
