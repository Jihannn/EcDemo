package com.jihan.moni_ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.main.sort.SortFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author Jihan
 * @date 2019/8/18
 */
public class MenuListFragment extends MiniDelegate {

    @BindView(R2.id.rv_sort_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.fragment_sort_menu_list;
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAnimation(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecycleView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("sort_list")
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        ArrayList<MultipleItemEntity> entityArrayList = new MenuListDataConverter()
                                .setJsonData(response).convert();
                        final SortFragment sortFragment = getParentDelegate();
                        final SortMenuRecyclerAdapter adapter = new SortMenuRecyclerAdapter(entityArrayList, sortFragment);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .loader(getContext())
                .build()
                .get();
    }
}
