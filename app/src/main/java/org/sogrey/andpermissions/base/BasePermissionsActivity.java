package org.sogrey.andpermissions.base;


import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import org.sogrey.andpermissions.impl.PermissionsResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * Created by Sogrey on 2018/8/29.
 */

public class BasePermissionsActivity extends AppCompatActivity {
    private PermissionsResultListener mListener;
    private int mRequestCode;
    private List<String> mListPermissions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void checkPermissions(String[] permissions, int requestCode, PermissionsResultListener listener) {
        mListener = listener;
        mRequestCode = requestCode;
        //检查权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //权限不能为空
            if (null != permissions && permissions.length > 0) {

                for (String permission : permissions)
                    if (!isHavePermissions(permission)) mListPermissions.add(permission);

                //遍历完后申请
                applyPermissions();
            }
        } else {
            mListener.onSuccessful();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == mRequestCode) {
            if (grantResults.length > 0) {
                mListener.onSuccessful(grantResults);
            } else {
                mListener.onFailure();
            }
        }
    }

    //判断权限是否申请
    private boolean isHavePermissions(String permissions) {
        if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    //申请权限
    private void applyPermissions() {
        if (!mListPermissions.isEmpty()) {
            int size = mListPermissions.size();
            ActivityCompat.requestPermissions(this, mListPermissions.toArray(new String[size]), mRequestCode);
        }
    }
}
