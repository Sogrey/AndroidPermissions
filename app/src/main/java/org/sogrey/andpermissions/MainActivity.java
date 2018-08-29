package org.sogrey.andpermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.sogrey.andpermissions.base.BasePermissionsActivity;
import org.sogrey.andpermissions.impl.PermissionsResultListener;

public class MainActivity extends BasePermissionsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_get_permission).setOnClickListener(v -> checkPermissions(new String[]{Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA,}, 300, new PermissionsResultListener() {
            @Override
            public void onSuccessful(int[] grantResults) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onSuccessful() {
                Toast.makeText(MainActivity.this, "Android 6.0以下默认授予权限", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
