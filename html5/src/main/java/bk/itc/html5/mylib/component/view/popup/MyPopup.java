package bk.itc.html5.mylib.component.view.popup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import bk.itc.html5.mylib.R;
import bk.itc.html5.mylib.component.util.DimenUtil;
import bk.itc.html5.mylib.component.util.DrawableUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Hien on 5/4/2018.
 */

public class MyPopup {
    public static void showNotify(String content, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int px = (int) DimenUtil.pxFromDp(20);
        textView.setPadding(px/3, px, px/3, px);
        textView.setTextSize(13);
        textView.setTextColor(Color.BLACK);
        textView.setText(content);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(textView);

        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setCancelable(true);
        sweetAlertDialog.hideConfirmButton()
                .setCustomView(scrollView)
                .show();
    }

    public static void showNotify(Spanned html, Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int px = (int) DimenUtil.pxFromDp(20);
        textView.setPadding(px/3, px, px/3, px);
        textView.setTextSize(13);
        textView.setTextColor(Color.BLACK);
        textView.setText(html);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(textView);

        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setCancelable(true);
        sweetAlertDialog.hideConfirmButton()
                .setCustomView(scrollView)
                .show();
    }

    public static void showRatingPrompt(final Context context, int photoId) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int px = (int) DimenUtil.pxFromDp(15);
        textView.setPadding(px/3, px, px/3, px);
        textView.setTextSize(13);
        textView.setTextColor(Color.BLACK);
        textView.setText("If you like this app, leave a review for us!");
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) DimenUtil.pxFromDp(200));
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(context).load(photoId).into(imageView);

        TextView rate = new TextView(context);
        RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        rate.setLayoutParams(rParams);
        px = (int) DimenUtil.pxFromDp(12);
        rate.setPadding(px*2, px, px*2, px);
        rate.setTextSize(13);
        rate.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        rate.setTextColor(Color.WHITE);
        rate.setText("Rate");
        rate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        rate.setBackground(DrawableUtil.createBackground(context.getResources().getColor(android.R.color.holo_green_dark), 3, 0, Color.WHITE));

        TextView refuse = new TextView(context);
        RelativeLayout.LayoutParams lParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lParams.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
        refuse.setLayoutParams(lParams);
        px = (int) DimenUtil.pxFromDp(12);
        refuse.setPadding(px*2, px, px*2, px);
        refuse.setTextSize(13);
        refuse.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        refuse.setTextColor(Color.WHITE);
        refuse.setText("Later");
        refuse.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        refuse.setBackground(DrawableUtil.createBackground(context.getResources().getColor(android.R.color.holo_red_dark), 3, 0, Color.WHITE));

        RelativeLayout bottomLayout = new RelativeLayout(context);
        bottomLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        px = (int) DimenUtil.pxFromDp(12);
        bottomLayout.setPadding(px, px, px, px);
        bottomLayout.addView(rate);
        bottomLayout.addView(refuse);

        LinearLayout relativeLayout = new LinearLayout(context);
        relativeLayout.setOrientation(LinearLayout.VERTICAL);
        relativeLayout.setHorizontalGravity(Gravity.CENTER);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        relativeLayout.addView(textView);
        relativeLayout.addView(imageView);
        relativeLayout.addView(bottomLayout);

        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.hideConfirmButton()
                .setCustomView(relativeLayout)
                .show();

        refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sweetAlertDialog.dismiss();
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
    }
}
