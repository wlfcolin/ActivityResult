package me.andy5.activity_result_listener.demo;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import me.andy5.activity_result_listener.ActivityResult;
import me.andy5.activity_result_listener.OnActivityResultListener;

/**
 * @author andy(Andy)
 * @datetime 2018-04-26 21:13 GMT+8
 * @email 411086563@qq.com
 */
public class TestService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(getApplicationContext(), "TestService started", Toast.LENGTH_SHORT).show();

        // get some result after started in service
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent startIntent = new Intent();
                    startIntent.setAction("me.andy5.activity_result_listener.demo.ACTION_GET_RESULT");
                    ActivityResult.startActivityForResult(TestService.this, startIntent, new OnActivityResultListener
                            () {
                        @Override
                        public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
                            Toast.makeText(getApplicationContext(), "data.result:" + data.getStringExtra("result"),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                }
            }
        }).start();
    }
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
