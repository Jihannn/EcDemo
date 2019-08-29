package com.jihan.moni_ec.main.index;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.ui.banner.BannerCreator;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.main.index.banner.BannerCreators;
import com.jihan.moni_ec.main.index.data.DataEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author Jihan
 * @date 2019/8/27
 */
public class IndexDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemClickListener {

    private static final int VIEW_EMPTY = 1;
    private static final int VIEW_BANNER = 2;
    private static final int VIEW_ARTICLE = 3;

    private final ArrayList<DataEntity> DATAS = new ArrayList<>();
    private final ArrayList<DataEntity> BANNERS = new ArrayList<>();

    private int mBannerCount = 0;
    private boolean isBanner = false;


    public void addData(ArrayList<DataEntity> data) {
        DATAS.addAll(data);
    }

    public void addBannerData(ArrayList<DataEntity> data) {
        BANNERS.addAll(data);
        mBannerCount += data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && BANNERS.size() > 0) {
            return VIEW_BANNER;
        }
        return VIEW_ARTICLE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;

        if (viewType == VIEW_BANNER) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_banner, viewGroup, false);
            return new BannerHolder(view);
        } else if (viewType == VIEW_ARTICLE) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_article, viewGroup, false);
            return new DataHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof BannerHolder && !isBanner) {
//            Mini.showToast("banner : " + position);
            ConvenientBanner convenientBanner = ((BannerHolder) viewHolder).convenientBanner;
            final ArrayList<String> bannersImages = new ArrayList<>();
            for (int i = 0; i < BANNERS.size(); i++) {
                bannersImages.add(BANNERS.get(i).getBannerImagePath());
            }
            BannerCreators.setDefault(convenientBanner, bannersImages, this);
            isBanner = true;
        }
        if (viewHolder instanceof DataHolder) {
            final DataEntity data = DATAS.get(position);
            final DataHolder holder = (DataHolder) viewHolder;
//            holder.mIcAuthorIcon.setText();
            holder.itemView.setOnClickListener(v -> Mini.showToast("点击了" + position));
            holder.mIcHeart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.mTvAuthorName.setText(data.getAuthor());
            holder.mTvParentGroup.setText(data.getSuperChapterName());
            holder.mTvChildGroup.setText(data.getChapterName());
            holder.mTvTitle.setText(data.getTitle());
            holder.mTvDate.setText(data.getNiceDate());
        }
    }

    @Override
    public int getItemCount() {
        return DATAS.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        public IconTextView mIcAuthorIcon;
        public TextView mTvAuthorName;
        public TextView mTvTitle;
        public IconTextView mIcHeart;
        public TextView mTvParentGroup;
        public TextView mTvChildGroup;
        public TextView mTvDate;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            mIcAuthorIcon = itemView.findViewById(R.id.icon_author_icon);
            mTvAuthorName = itemView.findViewById(R.id.tv_author_name);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIcHeart = itemView.findViewById(R.id.icon_heart);
            mTvParentGroup = itemView.findViewById(R.id.tv_parent_group);
            mTvChildGroup = itemView.findViewById(R.id.tv_child_group);
            mTvDate = itemView.findViewById(R.id.tv_date);
        }
    }

    public static class BannerHolder extends RecyclerView.ViewHolder {
        ConvenientBanner convenientBanner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            convenientBanner = itemView.findViewById(R.id.banner_recycler_item);
        }
    }
}
