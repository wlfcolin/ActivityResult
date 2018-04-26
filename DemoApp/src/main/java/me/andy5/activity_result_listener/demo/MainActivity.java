package me.andy5.activity_result_listener.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import me.andy5.activity_result_listener.ActivityResult;
import me.andy5.activity_result_listener.OnActivityResultListener;

/**
 * @author andy(Andy)
 * @datetime 2017-08-01 16:38 GMT+8
 * @email 411086563@qq.com
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnStartFromActivity;
    private Button mBtnStartFromService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStartFromActivity = (Button) findViewById(R.id.btn_start_from_activity);
        mBtnStartFromService = (Button) findViewById(R.id.btn_start_from_service);

        mBtnStartFromActivity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startFromActivity();
            }
        });
        mBtnStartFromService.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startFromService();
            }
        });
    }

    private void startFromActivity() {
        Bundle extras = new Bundle();
        extras.putString("hello", "hi");
        ActivityResult.startActivityForResult(this, TargetActivity.class, extras, new OnActivityResultListener() {
            @Override
            public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
                Toast.makeText(getApplicationContext(), "data.result:" + data.getStringExtra("result"), Toast
                        .LENGTH_SHORT).show();
            }
        });
    }

    private void startFromService() {
        Intent intent = new Intent(this, TestService.class);
        startService(intent);
    }
}
