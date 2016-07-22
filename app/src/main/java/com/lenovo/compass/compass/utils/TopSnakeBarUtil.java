package com.lenovo.compass.compass.utils;

import android.view.View;

import com.lenovo.compass.compass.CompassApplication;
import com.lenovo.compass.compass.widget.topsnackbar.KsyunTopSnackBar;

public class TopSnakeBarUtil {
    public static final int SNAKE_BAR_SP_SIZE = 12;

    public static void showSnakeBar(String mSnakeBarContent, View view) {
//        TSnackbar snackBar = TSnackbar.make( view,mSnakeBarContent, TSnackbar.LENGTH_LONG);
//        snackBar.setActionTextColor(Color.WHITE);
//        View snackBarView = snackBar.getView();
//        snackBarView.setBackgroundColor(view.getResources().getColor(R.color.kingsoft_red));
//        TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, SNAKE_BAR_SP_SIZE);
//        snackBar.show();

        KsyunTopSnackBar.make(CompassApplication.getInstance(), mSnakeBarContent, KsyunTopSnackBar.LENGTH_LONG).show();
    }
}
