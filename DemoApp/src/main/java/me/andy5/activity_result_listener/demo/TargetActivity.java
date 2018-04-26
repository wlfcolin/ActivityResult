package me.andy5.activity_result_listener.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author andy(Andy)
 * @datetime 2017-08-01 16:38 GMT+8
 * @email 411086563@qq.com
 */
public class TargetActivity extends Activity {

    private EditText mEtResult;
    private Button mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            Toast.makeText(getApplicationContext(), "extra:" + extra.getString("hello"), Toast.LENGTH_SHORT).show();
        }

        mEtResult = (EditText) findViewById(R.id.et_result);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                String result = mEtResult.getText().toString();
                data.putExtra("result", result);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}

