package com.jihan.moni_ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jihan.mini_core.delegates.bottom.BottomItemDelegate;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.main.personal.list.OrderListFragment;
import com.jihan.moni_ec.main.personal.profile.UserProfileFragment;
import com.jihan.moni_ec.main.personal.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class PersonalFragment extends BottomItemDelegate {

    @BindView(R2.id.rv_personal_setting)
    RecyclerView mRecycleView = null;

//    @OnClick(R2.id.tv_all_order)
//    void onClickAllOrder() {
//        getParentDelegate().getSupportDelegate().start(OrderListFragment.newIntent("all"));
//    }

    @OnClick(R2.id.img_user_avatar)
    void onClickUserProfile() {
        getParentDelegate().getSupportDelegate().start(new UserProfileFragment());
    }

    public static final String ORDER_TYPE = "ORDER_TYPE";

    private PersonalAdapter mPersonalAdapter;
    private LinearLayoutManager manager;


    @Override
    public Object setLayout() {
        return R.layout.fragment_tab_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPersonalAdapter = new PersonalAdapter(createLowList());
        manager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(manager);
        mRecycleView.setAdapter(mPersonalAdapter);
        mRecycleView.addOnItemTouchListener(new PersonalItemClickListener(this));
    }

    private List<ListBean> createLowList() {

        ListBean system = new ListBean.Builder()
                .setItemType(ItemType.ITEM_NORMAL)
                .setId(R.id.item_personal_setting)
                .setText("系统设置")
                .setMiniDelegate(new SettingsFragment())
                .build();

        ListBean logout = new ListBean.Builder()
                .setItemType(ItemType.ITEM_LOGOUT)
                .setId(2)
                .setText("注销")
                .build();


        List<ListBean> list = new ArrayList<>();
        list.add(system);
        list.add(logout);
        return list;
    }
}
