package com.example.androidassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.wuxiaolong.androidutils.library.VersionUtil;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    final static int REQUECT_CODE_SDCARD = 2;
    TextView mTv;
    Button mBtn1,mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mTv = findViewById(R.id.tv);
            mBtn1 = findViewById(R.id.btn1);
            mBtn2 = findViewById(R.id.btn2);


            mBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this,"Text",Toast.LENGTH_SHORT).show();
            }
        });
            mTv.setText(VersionUtil.getVersionName(getApplicationContext()));
            mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // MPermissions.requestPermissions(MainActivity.this, REQUECT_CODE_SDCARD, Manifest.permission.WRITE_EXTERNAL_STORAGE);




                    XXPermissions.with(MainActivity.this)
                            // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                            //.constantRequest()
                            // 支持请求6.0悬浮窗权限8.0请求安装权限
                            .permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                            // 不指定权限则自动获取清单中的危险权限
                            .permission(Permission.Group.STORAGE)
                            .request(new OnPermission() {

                                @Override
                                public void hasPermission(List<String> granted, boolean isAll) {

                                    Toast.makeText(MainActivity.this,"有权限",Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void noPermission(List<String> denied, boolean quick) {
                                    Toast.makeText(MainActivity.this,"无权限",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });

    }

 /*   @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/

/*
    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess()
    {
        Toast.makeText(this, "GRANT ACCESS SDCARD!", Toast.LENGTH_SHORT).show();
    }
*/



}
