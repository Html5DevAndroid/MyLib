package bk.itc.html5.mylib.component.util;

import android.graphics.drawable.GradientDrawable;

/**
 * Created by Hien on 5/2/2018.
 */

public class DrawableUtil {
    public static GradientDrawable createBackground(int color, int radiusInDp, int stroke, int strokeColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(DimenUtil.pxFromDp((float)radiusInDp));
        shape.setColor(color);
        shape.setStroke(stroke, strokeColor);

        return shape;
    }
}
