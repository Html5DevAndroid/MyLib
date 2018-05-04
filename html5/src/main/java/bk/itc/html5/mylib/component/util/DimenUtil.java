package bk.itc.html5.mylib.component.util;

import android.app.Activity;
import android.util.DisplayMetrics;

import bk.itc.html5.mylib.component.MyApplication;

/**
 * Created by Hien on 4/19/2018.
 */

public class DimenUtil {
    public static float dpFromPx(final float px) {
        return px / MyApplication.getContext().getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final float dp) {
        return dp * MyApplication.getContext().getResources().getDisplayMetrics().density;
    }

    public static int[] getScreenSize(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        return new int[]{width, height};
    }
}
