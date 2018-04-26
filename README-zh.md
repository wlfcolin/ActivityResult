# ActivityResult

一个用于android的简单实用start activity for result辅助库，很简单的api实现将Activity.startActivityForResult转成listener监听模式调用


----------------------------------------------------------------------

**快速开始**
* 1. 在项目app模块的build.gradle配置gradle
``` java
compile 'me.andy5:ActivityResult:0.1.0'
```

* 2. 创建一个获取数据的目标界面，写好你的数据逻辑
``` java
public class TargetActivity extends Activity {
}
```

* 3. 如果获取数据成功，在结束数据界面前调用一下setResult(RESULT_OK, data)即可
``` java
Intent data = new Intent();
String result = mEtResult.getText().toString();
data.putExtra("result", result);
setResult(Activity.RESULT_OK, data);
finish();
```


* 4. 首先， 在需要调到获取数据界面的地方调用startActivityForResult(Context context, Class<? extends Activity> activity, Bundle extras,
OnActivityResultListener listener) 或 startActivityForResult(Context context, Intent intent, OnActivityResultListener listener)
``` java
ActivityResult.startActivityForResult(this, TargetActivity.class, extras, new OnActivityResultListener() {...});
ActivityResult.startActivityForResult(TestService.this, startIntent, new OnActivityResultListener() {...});
```


* 5. 在OnActivityResultListener做获取数据逻辑
``` java
Bundle extras = new Bundle();
extras.putString("hello", "hi");
ActivityResult.startActivityForResult(this, TargetActivity.class, extras, new OnActivityResultListener() {
        @Override
        public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
            Log.e("andy", "data.result:" + data.getStringExtra("result"));
        }
    });
Intent startIntent = new Intent();
startIntent.setAction("me.andy5.activity_result_listener.demo.ACTION_GET_RESULT");
ActivityResult.startActivityForResult(TestService.this, startIntent, new OnActivityResultListener() {
    @Override
    public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        Log.e("andy", "data.result:" + data.getStringExtra("result"));
    }
});
```


----------------------------------------------------------------------
**LICENSE**
```
Copyright 2018 wlfcolin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

