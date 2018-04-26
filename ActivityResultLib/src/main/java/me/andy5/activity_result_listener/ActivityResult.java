package me.andy5.activity_result_listener;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * support to start a activity with activity result listener
 *
 * @author andy(Andy)
 * @datetime 2017-08-01 16:38 GMT+8
 * @email 411086563@qq.com
 */
public class ActivityResult {

    /**
     * start a activity with activity result listener
     *
     * @param context     the context
     * @param activity    the target activity class to start
     * @param extras      the extras to send to target activity, you can use {@link Intent#getExtras()} to get this
     *                    extras
     * @param requestCode the requestCode
     * @param listener    activity result listener
     */
    public static void startActivityForResult(Context context, Class<? extends Activity> activity, Bundle extras, int
            requestCode, OnActivityResultListener listener) {
        ActivityResultManager.getInstance().request(context, activity, extras, requestCode, listener);
    }

    /**
     * start a activity with activity result listener
     *
     * @param context  the context
     * @param activity the target activity class to start
     * @param extras   the extras to send to target activity, you can use {@link Intent#getExtras()} to get this extras
     * @param listener activity result listener
     */
    public static void startActivityForResult(Context context, Class<? extends Activity> activity, Bundle extras,
                                              OnActivityResultListener listener) {
        startActivityForResult(context, activity, extras, Integer.MAX_VALUE, listener);
    }

    /**
     * start a activity with activity result listener
     *
     * @param context     the context
     * @param intent      the intent to start activity class, the intent without
     *                    {@link Intent#setClassName(String, String)} and
     *                    {@link Intent#setClassName(Context, String)} and
     *                    {@link Intent#setComponent(ComponentName)}, you can only use the intent by setting
     *                    {@link Intent#setAction(String)}, if you have the target class to start, use
     *                    {@link ActivityResult#startActivityForResult(Context, Class, Bundle, int, OnActivityResultListener)} instead
     * @param requestCode the requestCode
     * @param listener    activity result listener
     */
    public static void startActivityForResult(Context context, Intent intent, int requestCode,
                                              OnActivityResultListener listener) {
        ActivityResultManager.getInstance().request(context, intent, requestCode, listener);
    }

    /**
     * start a activity with activity result listener
     *
     * @param context  the context
     * @param intent   the intent to start activity class, the intent without
     *                 {@link Intent#setClassName(String, String)} and {@link Intent#setClassName(Context, String)} and
     *                 {@link Intent#setComponent(ComponentName)}, you can only use the intent by setting
     *                 {@link Intent#setAction(String)}, if you have the target class to start, use
     *                 {@link ActivityResult#startActivityForResult(Context, Class, Bundle, int, OnActivityResultListener)} instead
     * @param listener activity result listener
     */
    public static void startActivityForResult(Context context, Intent intent, OnActivityResultListener listener) {
        startActivityForResult(context, intent, Integer.MAX_VALUE, listener);
    }
}
