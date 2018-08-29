package org.sogrey.andpermissions.impl;

/**
 * 描述：
 * Created by Sogrey on 2018/8/29.
 */

public interface PermissionsResultListener {

    /**
     * 授权成功
     */
    void onSuccessful(int[] grantResults);

    /**
     * Android 6.0以下设备默认成功
     */
    void onSuccessful();

    /**
     * 授权失败
     */
    void onFailure();
}