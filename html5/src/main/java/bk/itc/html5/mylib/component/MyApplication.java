package bk.itc.html5.mylib.component;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.pixplicity.easyprefs.library.Prefs;

import bk.itc.html5.mylib.component.data.MyPreference;

/**
 * Created by Hien on 5/1/2018.
 */

public class MyApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        MyPreference.updateOpenTimesCount();
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}
