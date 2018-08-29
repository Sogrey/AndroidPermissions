package org.sogrey.rxpermissionsapp;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_get_peimission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissions();
            }
        });
    }

    private void getPermissions() {
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
//        rxPermissions
//                .request(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA)
//                .subscribe(granted -> {
//                    if (granted) {
//                        // All requested permissions are granted
//                        Toast.makeText(MainActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // At least one permission is denied
//                        Toast.makeText(MainActivity.this, "至少有一个权限被拒绝", Toast.LENGTH_SHORT).show();
//                    }
//                });
        rxPermissions
                .requestEach(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA)
                .subscribe(permission -> { // will emit 2 Permission objects
                    if (permission.granted) {
                        // `permission.name` is granted !
                        Toast.makeText(MainActivity.this, "同意权限:"+permission.name, Toast.LENGTH_SHORT).show();
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        // Denied permission without ask never again
                        Toast.makeText(MainActivity.this, "拒绝不允许再也不允许", Toast.LENGTH_SHORT).show();
                    } else {
                        // Denied permission with ask never again
                        // Need to go to the settings
                        Toast.makeText(MainActivity.this, "拒绝同意再询问，需要去设置", Toast.LENGTH_SHORT).show();
                    }
                });
//        rxPermissions
//                .requestEachCombined(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA)
//                .subscribe(permission -> { // will emit 1 Permission object
//                    if (permission.granted) {
//                        // All permissions are granted !
//                        Toast.makeText(MainActivity.this, "同意权限:"+permission.name, Toast.LENGTH_SHORT).show();
//                    } else if (permission.shouldShowRequestPermissionRationale) {
//                        // At least one denied permission without ask never again
//                        Toast.makeText(MainActivity.this, "至少有一个拒绝不允许再也不允许", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // At least one denied permission with ask never again
//                        // Need to go to the settings
//                        Toast.makeText(MainActivity.this, "至少有一个拒绝同意，要求再也不要，需要去设置", Toast.LENGTH_SHORT).show();
//                    }
//                });
//        rxPermissions
//                .request(Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA)
//                .subscribe(new Observer<Boolean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Boolean value) {
//                        if (value) {
//                            Toast.makeText(MainActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
