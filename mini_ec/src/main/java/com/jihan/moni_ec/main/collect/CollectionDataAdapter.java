package com.jihan.moni_ec.main.collect;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.configs.ApiConfigs;
import com.jihan.moni_ec.main.index.IndexWebDelegate;
import com.jihan.moni_ec.main.index.data.DataEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

public class CollectionDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VIEW_EMPTY = 1;
    private static final int VIEW_ARTICLE = 2;
    private static final int VIEW_LOAD_MORE = 3;

    private final ArrayList<DataEntity> DATAS = new ArrayList<>();
    private MiniDelegate DELEGATE = null;

    private boolean isLoadMore = true;

    public CollectionDataAdapter(MiniDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public void addData(ArrayList<DataEntity> data) {
        DATAS.addAll(data);
    }

    public void setLoadMore(boolean isFull) {
        isLoadMore = isFull;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == DATAS.size() && isLoadMore) {
            return VIEW_LOAD_MORE;
        }
        return VIEW_ARTICLE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == VIEW_ARTICLE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_article, viewGroup, false);
            return new DataHolder(view);
        } else if (viewType == VIEW_LOAD_MORE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_load_more, viewGroup, false);
            return new LoadMoreHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof DataHolder) {
            final DataEntity data = DATAS.get(position);
            final DataHolder holder = (DataHolder) viewHolder;
            final int id = DATAS.get(position).getId();
            final int originId = DATAS.get(position).getOriginId();
            final boolean[] isCollect = {true};
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (DELEGATE != null) {
                        DELEGATE.getParentDelegate().getSupportDelegate().start(IndexWebDelegate.newIntent(data.getLink()));
                    }
                }
            });
            holder.mIcHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isCollect[0]) {
                        RestClient.builder()
                                .url(ApiConfigs.COLLECTION + id + "/json")
                                .success(new ISuccess() {
                                    @Override
                                    public void success(String response) {
                                        isCollect[0] = true;
                                        holder.mIcHeart.setTextColor(Color.RED);
                                        Mini.showToast("收藏成功");
                                    }
                                })
                                .error(new IError() {
                                    @Override
                                    public void error(int code, String msg) {
                                        Mini.showToast("code : " + code + "\nmsg : " + msg);
                                    }
                                })
                                .build()
                                .post();
                    } else {
                        RestClient.builder()
                                .url(ApiConfigs.UN_COLLECTION_LIST + id + "/json")
                                .params("originId",originId)
                                .success(new ISuccess() {
                                    @Override
                                    public void success(String response) {
                                        isCollect[0] = false;
                                        holder.mIcHeart.setTextColor(Color.GRAY);
                                        Mini.showToast("取消收藏");
                                    }
                                })
                                .error(new IError() {
                                    @Override
                                    public void error(int code, String msg) {
                                        Mini.showToast("code : " + code + "\nmsg : " + msg);
                                    }
                                })
                                .build()
                                .post();
                    }
                }
            });
            holder.mTvAuthorName.setText(data.getAuthor());
            holder.mTvChildGroup.setText(data.getChapterName());
            holder.mTvTitle.setText(data.getTitle());
            holder.mTvDate.setText(data.getNiceDate());
            if(isCollect[0]){
                holder.mIcHeart.setTextColor(Color.RED);
            }else{
                holder.mIcHeart.setTextColor(Color.GRAY);
            }
        }
        if (viewHolder instanceof LoadMoreHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return DATAS.size();
    }


    public static class DataHolder extends RecyclerView.ViewHolder {
        public IconTextView mIcAuthorIcon;
        public TextView mTvAuthorName;
        public TextView mTvTitle;
        public IconTextView mIcHeart;
        public TextView mTvChildGroup;
        public TextView mTvDate;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            mIcAuthorIcon = itemView.findViewById(R.id.icon_author_icon);
            mTvAuthorName = itemView.findViewById(R.id.tv_author_name);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIcHeart = itemView.findViewById(R.id.icon_heart);
            mTvChildGroup = itemView.findViewById(R.id.tv_child_group);
            mTvDate = itemView.findViewById(R.id.tv_date);
        }
    }


    public static class LoadMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadMoreHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.pb_load_more);
        }
    }
}
