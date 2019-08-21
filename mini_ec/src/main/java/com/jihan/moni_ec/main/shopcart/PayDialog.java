package com.jihan.moni_ec.main.shopcart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.moni_ec.R;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class PayDialog {

    private AlertDialog mDialog = null;

    private PayDialog(MiniDelegate delegate) {
        this.mDialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public static PayDialog create(MiniDelegate delegate) {
        return new PayDialog(delegate);
    }

    public void beginDialog() {
        mDialog.show();
        Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_pay_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setWindowAnimations(R.style.dialog_pay_panel);

            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
        }
        mDialog.findViewById(R.id.btn_dialog_pay_wechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mini.showToast("微信支付");
            }
        });

        mDialog.findViewById(R.id.btn_dialog_pay_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.cancel();
            }
        });
    }
}
