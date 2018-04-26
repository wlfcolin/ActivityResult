package me.andy5.activity_result_listener;

import android.content.Context;
import android.content.Intent;

/**
 * listener for login result
 *
 * @author andy(Andy)
 * @datetime 2017-08-01 16:47 GMT+8
 * @email 411086563@qq.com
 */
public interface OnActivityResultListener {

    /**
     * on activity result listener
     *
     * @param context     the context to start activity
     * @param requestCode the requestCode
     * @param resultCode  the resultCode
     * @param data        the for result data
     */
    void onActivityResult(Context context, int requestCode, int resultCode, Intent data);
}
