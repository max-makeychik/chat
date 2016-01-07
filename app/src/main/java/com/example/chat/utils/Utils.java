package com.example.chat.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class Utils {

    public static boolean isNetworkConnected(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();

            SharedPreferences wifi = PreferenceManager.getDefaultSharedPreferences(context);
            if (info != null) {
                if (!wifi.getBoolean("wifipref", false))
                    return info.isConnectedOrConnecting();
                else
                    return (info.getType() == ConnectivityManager.TYPE_WIFI && info.isConnectedOrConnecting());
            } else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int spToPixels(Context context, float sp) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scaledDensity);
    }

    public static int dpToPixels(Context context, int dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }

  public static int getMargin(Context context){
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    int width = size.x;
    return (int) (width * 0.4);
  }
}