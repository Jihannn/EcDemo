package com.jihan.mini_core.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Jihan
 * @date 2019/8/22
 */
public class DateDialogUtil {

    public interface IDateListener {
        void dateChange(String date);
    }

    private IDateListener mListener;
    private String mDate = null;

    public DateDialogUtil setListener(IDateListener listener) {
        this.mListener = listener;
        return this;
    }

    public void showDialog(Context context) {
        final LinearLayout linearLayout = new LinearLayout(context);
        final DatePicker picker = new DatePicker(context);
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        picker.setLayoutParams(params);

        picker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                String date = format.format(calendar.getTime());
                mDate = date;
            }
        });

        linearLayout.addView(picker);

        new AlertDialog.Builder(context)
                .setTitle("请选择日期")
                .setView(linearLayout)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mListener != null) {
                            mListener.dateChange(mDate);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
