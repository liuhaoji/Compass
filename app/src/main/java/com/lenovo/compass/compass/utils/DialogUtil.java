package com.lenovo.compass.compass.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ksyun.android.ddlive.R;

public class DialogUtil {

    private static DialogUtil instants;
    private Activity mActivity;
    private LayoutInflater mInflater;

    private DialogUtil(Activity activity) {
        mActivity = activity;
    }

    public static DialogUtil getInstants(Activity activity) {
        if (instants == null) {
            return new DialogUtil(activity);
        } else {
            return instants;
        }
    }

    public Dialog CreateDialog(String title, String leftBtnText, String rightBtnText, DialogClick listener2) {
        return initDialog(title, null, leftBtnText, rightBtnText, null, listener2, true);
    }

    public Dialog CreateDialog(String title, String leftBtnText, String rightBtnText, DialogClick listener2, boolean isCancelable) {
        return initDialog(title, null, leftBtnText, rightBtnText, null, listener2, isCancelable);
    }

    public Dialog CreateDialog(String title, String message, String leftBtnText, String rightBtnText, DialogClick listener1, DialogClick listener2, boolean isCancelable) {
        return initDialog(title, message, leftBtnText, rightBtnText, listener1, listener2, isCancelable);
    }

    public Dialog initDialog(String title, String message, String leftBtnText, String rightBtnText, final DialogClick listener1, final DialogClick listener2, boolean isCancelable) {
        final Dialog dialog = new Dialog(mActivity, R.style.MyDialog);
        dialog.setCancelable(isCancelable);
        View v = mActivity.getLayoutInflater().inflate(R.layout.pop_dialog, null);
        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        TextView tvMessage = (TextView) v.findViewById(R.id.tvMessage);
        Button btnLeft = (Button) v.findViewById(R.id.btnLeft);
        Button btnRight = (Button) v.findViewById(R.id.btnRight);
        tvTitle.setText(title);
        if (message == null) {
            tvMessage.setVisibility(View.GONE);
        } else {
            tvMessage.setText(message);
        }
        btnLeft.setText(leftBtnText);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener1 != null) {
                    listener1.onClick();
                }
            }
        });
        btnRight.setText(rightBtnText);
        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener2 != null) {
                    listener2.onClick();
                }
            }
        });
        dialog.setContentView(v);
        dialog.show();
        return dialog;
    }

    public interface DialogClick {
        void onClick();
    }
}
