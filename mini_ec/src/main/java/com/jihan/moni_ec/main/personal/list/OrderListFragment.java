package com.jihan.moni_ec.main.personal.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.recycler.DataConverter;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.main.personal.PersonalFragment;

import butterknife.BindView;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class OrderListFragment extends MiniDelegate {

    @BindView(R2.id.rv_order_list)
    RecyclerView mRecyclerView = null;

    private String mType = null;

    @Override
    public Object setLayout() {
        return R.layout.fragment_order_list;
    }

    public static OrderListFragment newIntent(String type){
        Bundle args = new Bundle();
        args.putString(PersonalFragment.ORDER_TYPE,type);
        OrderListFragment orderListFragment = new OrderListFragment();
        orderListFragment.setArguments(args);
        return orderListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mType = args.getString(PersonalFragment.ORDER_TYPE);
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("order_list")
//                .params("type",mType)
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        DataConverter dataConverter = new OrderListDataConverter().setJsonData(response);
                        OrderListAdapter orderListAdapter = new OrderListAdapter(dataConverter.convert());
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setAdapter(orderListAdapter);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void failure(String msg) {
                        Mini.showToast(msg);
                    }
                })
                .build()
                .get();
    }
}
