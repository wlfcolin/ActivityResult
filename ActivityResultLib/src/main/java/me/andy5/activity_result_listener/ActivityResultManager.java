package me.andy5.activity_result_listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * result activity manager
 *
 * @author andy(Andy)
 * @datetime 2018-04-23 11:35 GMT+8
 * @email 411086563@qq.com
 */
class ActivityResultManager {

    // key for session id
    public static final String EXTRA_KEY_SESSION_ID = "ActivityResultManager.SessionId";

    private static ActivityResultManager sInstance;

    // SessionId map with WrapIntent
    private Map<String, WrapIntent> mWrapIntentMap = new HashMap();

    // private constructor
    private ActivityResultManager() {
    }

    /**
     * single instance
     *
     * @return
     */
    static ActivityResultManager getInstance() {
        if (sInstance == null) {
            synchronized (ActivityResultManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityResultManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * request to start activity for result
     *
     * @param context
     * @param activity
     * @param extras
     * @param requestCode
     * @param listener
     */
    void request(Context context, Class<? extends Activity> activity, Bundle extras, int requestCode,
                 OnActivityResultListener listener) {
        Intent intent = new Intent(context, WrapActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        String sessionId = UUID.randomUUID().toString();
        WrapIntent wrapIntent = new WrapIntent(context, null, activity, requestCode, listener);
        mWrapIntentMap.put(sessionId, wrapIntent);

        intent.putExtra(EXTRA_KEY_SESSION_ID, sessionId);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    /**
     * request to start activity for result
     *
     * @param context
     * @param request
     * @param requestCode
     * @param listener
     */
    void request(Context context, Intent request, int requestCode, OnActivityResultListener listener) {
        Intent intent = new Intent(context, WrapActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        String sessionId = UUID.randomUUID().toString();
        WrapIntent wrapIntent = new WrapIntent(context, request, null, requestCode, listener);
        mWrapIntentMap.put(sessionId, wrapIntent);

        intent.putExtra(EXTRA_KEY_SESSION_ID, sessionId);
        context.startActivity(intent);
    }

    /**
     * start activity for result
     *
     * @param wrapActivity
     */
    void startActivityForResult(WrapActivity wrapActivity) {
        String sessionId = wrapActivity.getSessionId();
        WrapIntent wrapIntent = mWrapIntentMap.get(sessionId);

        Intent intent;
        if (wrapIntent.mActivity != null) {
            intent = new Intent(wrapActivity, wrapIntent.mActivity);
        } else {
            intent = wrapIntent.mIntent;
        }
        Bundle extras = wrapActivity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);// the extras send target
        }
        wrapActivity.startActivityForResult(intent, wrapIntent.mRequestCode);
    }

    /**
     * onActivityResult
     *
     * @param wrapActivity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    void onActivityResult(WrapActivity wrapActivity, int requestCode, int resultCode, Intent data) {
        String sessionId = wrapActivity.getSessionId();
        WrapIntent wrapIntent = mWrapIntentMap.get(sessionId);

        if (requestCode == wrapIntent.mRequestCode) {
            wrapActivity.finish();
            if (wrapIntent.mListener != null) {
                wrapIntent.mListener.onActivityResult(wrapIntent.mContext, requestCode, resultCode, data);
            }
            mWrapIntentMap.remove(sessionId);
        }
    }

    // wrap Intent
    private static class WrapIntent {

        private final Context mContext;
        private final Intent mIntent;
        private final Class<? extends Activity> mActivity;
        private final int mRequestCode;
        private final OnActivityResultListener mListener;

        WrapIntent(Context context, Intent intent, Class<? extends Activity> activity, int requestCode,
                   OnActivityResultListener listener) {
            mContext = context;
            mIntent = intent;
            mActivity = activity;
            mRequestCode = requestCode;
            mListener = listener;
        }
    }
}
