package com.lhr.day530_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lhr.data.SpecialtyChooseEntity;
import com.lhr.day530_test.R;
import com.lhr.day530_test.RoundImage;
import com.lhr.utils.newAdd.GlideUtil;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任小龙 on 2020/6/2.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private List<SpecialtyChooseEntity> mList;
    private Context mContext;

    public SubjectAdapter(List<SpecialtyChooseEntity> pList, Context pContext) {
        mList = pList;
        mContext = pContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_child_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SpecialtyChooseEntity entity = mList.get(position);
        GlideUtil.loadImage(holder.leftRoundImage,entity.getIcon());
        holder.itemTitle.setText(entity.getBigspecialty());
        holder.itemRecyclerview.setLayoutManager(new GridLayoutManager(mContext,4));
        holder.itemRecyclerview.setAdapter(new SubjectChildAdapter(entity.getData(),mContext,this));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_round_image)
        RoundImage leftRoundImage;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_recyclerview)
        RecyclerView itemRecyclerview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}