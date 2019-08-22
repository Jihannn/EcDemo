package com.jihan.moni_ec.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.main.personal.ItemType;
import com.jihan.moni_ec.main.personal.ListBean;
import com.jihan.moni_ec.main.personal.PersonalAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Jihan
 * @date 2019/8/22
 */
public class UserProfileFragment extends MiniDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.fragment_user_profile;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        PersonalAdapter adapter = new PersonalAdapter(createLowList());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new UserProfileItemClickListener(this));
    }

    private List<ListBean> createLowList() {

        ListBean avatar = new ListBean.Builder()
                .setItemType(ItemType.ITEM_AVATAR)
                .setId(1)
                .setImageUrl("https://uimgproxy.suning.cn/uimg1/sop/commodity/IQNRbBk5UTwYM4-UOtBFgQ.jpg")
                .build();

        ListBean name = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(2)
                .setText("姓名")
                .setValue("未設置")
                .build();

        ListBean sex = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(3)
                .setText("性別")
                .setValue("未設置")
                .build();

        ListBean birth = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(4)
                .setText("生日")
                .setValue("未設置")
                .build();

        List<ListBean> list = new ArrayList<>();
        list.add(avatar);
        list.add(name);
        list.add(sex);
        list.add(birth);
        return list;
    }
}
