package com.jihan.moni_ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.mini_core.util.MiniPreference;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Jihan
 * @date 2019/8/24
 */
public class SearchFragment extends MiniDelegate {
    @OnClick(R2.id.icon_top_search_back)
    void onClickBack() {
        getSupportDelegate().pop();
    }

    @BindView(R2.id.et_search_view)
    AppCompatEditText mEtSearch;

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView;

    @OnClick(R2.id.tv_top_search)
    void onClickSearch() {
        saveItem();
    }

    private void saveItem() {
        final String data = mEtSearch.getText().toString();
        if (!TextUtils.isEmpty(data)) {
            final String historyJson = MiniPreference.getCustomAppProfile(SearchDataConverter.SEARCH_HISTORY);
            List<String> historyList;
            if (TextUtils.isEmpty(historyJson)) {
                historyList = new ArrayList<>();
            } else {
                historyList = JSON.parseObject(historyJson, ArrayList.class);
            }
            historyList.add(data);
            String json = JSON.toJSONString(historyList);
            MiniPreference.addCustomAppProfile(SearchDataConverter.SEARCH_HISTORY, json);

            RestClient.builder()
                    .url("index1")
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void success(String response) {
                            Mini.showToast(response);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void failure(String msg) {
                            Mini.showToast(msg);
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void error(int code, String msg) {
                            Mini.showToast(code+msg);
                        }
                    })
                    .build()
                    .get();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        ArrayList<MultipleItemEntity> data = new SearchDataConverter().convert();
        SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration();
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20,20)
                        .color(Color.GRAY)
                        .build();
            }
        });

        mRecyclerView.addItemDecoration(itemDecoration);
    }
}
