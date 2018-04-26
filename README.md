# ActivityResult

a easy lib for android to start a activity for activity result and get the results by activity result listener


[中文说明文档](https://github.com/wlfcolin/ActivityResult/blob/master/README-zh.md)


----------------------------------------------------------------------

**Quick start**
* 1. add in dependencies in your app module's build.gradle
``` java
compile 'me.andy5:ActivityResult:0.1.0'
```

* 2. create a target activity for your business logic
``` java
public class TargetActivity extends Activity {
}
```

* 3. if your logic is success, call the setResult(RESULT_OK, data) before finish your activity
``` java
Intent data = new Intent();
String result = mEtResult.getText().toString();
data.putExtra("result", result);
setResult(Activity.RESULT_OK, data);
finish();
```


* 4. firstly, call startActivityForResult(Context context, Class<? extends Activity> activity, Bundle extras,
OnActivityResultListener listener) or startActivityForResult(Context context, Intent intent, OnActivityResultListener listener)
in your logic that need get target activity result
``` java
ActivityResult.startActivityForResult(this, TargetActivity.class, extras, new OnActivityResultListener() {...});
ActivityResult.startActivityForResult(TestService.this, startIntent, new OnActivityResultListener() {...});
```


* 5. do your result logic in the OnActivityResultListener
``` java
Bundle extras = new Bundle();
extras.putString("hello", "hi");
ActivityResult.startActivityForResult(this, TargetActivity.class, extras, new OnActivityResultListener() {
        @Override
        public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
            Toast.makeText(getApplicationContext(), "data.result:" + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
        }
    });
Intent startIntent = new Intent();
startIntent.setAction("me.andy5.activity_result_listener.demo.ACTION_GET_RESULT");
ActivityResult.startActivityForResult(TestService.this, startIntent, new OnActivityResultListener() {
    @Override
    public void onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        Toast.makeText(getApplicationContext(), "data.result:" + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
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

