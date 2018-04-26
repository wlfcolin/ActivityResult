package me.andy5.activity_result_listener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * inner warp activity
 *
 * @author andy(Andy)
 * @datetime 2017-08-01 16:35 GMT+8
 * @email 411086563@qq.com
 */
public class WrapActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityResultManager.getInstance().startActivityForResult(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultManager.getInstance().onActivityResult(this, requestCode, resultCode, data);
    }

    /**
     * get the session id
     *
     * @return
     */
    String getSessionId() {
        return getIntent().getStringExtra(ActivityResultManager.EXTRA_KEY_SESSION_ID);
    }
}
