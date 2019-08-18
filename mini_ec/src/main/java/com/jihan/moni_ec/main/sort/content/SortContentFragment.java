package com.jihan.moni_ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * @author Jihan
 * @date 2019/8/18
 */
public class SortContentFragment extends MiniDelegate {

    @BindView(R2.id.rv_sort_content)
    RecyclerView mRecyclerView;

    private static final String ARG_CONTENT_ID = "content_id";

    private int mContentId = -1;

    private List<SectionBean> mData = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static SortContentFragment newInstance(int contentInd) {
        Bundle args = new Bundle();
        args.putInt(ARG_CONTENT_ID, contentInd);
        SortContentFragment fragment = new SortContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_sort_content;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        initData();
    }

    private void initData() {
        RestClient.builder()
                .url("sort_content")
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        mData = new SortContentConverter().convert(response);
                        final SectionAdapter adapter = new SectionAdapter(R.layout.item_section_content, R.layout.item_section_header, mData);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .loader(getContext())
                .build()
                .get();
    }
}
