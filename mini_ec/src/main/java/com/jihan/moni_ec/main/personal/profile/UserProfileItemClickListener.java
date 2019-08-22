package com.jihan.moni_ec.main.personal.profile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.util.DateDialogUtil;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.main.personal.ListBean;
import com.jihan.moni_ec.main.personal.settings.UserNameFragment;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/22
 */
public class UserProfileItemClickListener extends SimpleClickListener {

    private final MiniDelegate DELEGATE;

    private final String[] GENDERS = {"男", "女", "保密"};

    public UserProfileItemClickListener(MiniDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean data = (ListBean) adapter.getData().get(position);
        int id = data.getId();
        switch (id) {
            case 1: {
                //TODO 开启照相机
                break;
            }
            case 2: {
                DELEGATE.getSupportDelegate().start(new UserNameFragment());
                break;
            }
            case 3: {
                setGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppCompatTextView value = view.findViewById(R.id.tv_arrow_value);
                        value.setText(GENDERS[which]);
                        dialog.cancel();
                    }
                });
                break;
            }
            case 4: {
                new DateDialogUtil().setListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void dateChange(String date) {
                        AppCompatTextView value = view.findViewById(R.id.tv_arrow_value);
                        value.setText(date);
                    }
                }).showDialog(DELEGATE.getContext());
                break;
            }
            default: {
                break;
            }
        }
    }

    private void setGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(GENDERS, 0, listener);
        builder.show();
    }


    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
