package com.jingchengsoft.dzjplatform.feature.common.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.jingchengsoft.dzjplatform.R;

import java.util.ArrayList;


public class PhotoAdapter extends BaseAdapter {
    private ArrayList<Photo> imgPaths;
    private Context mContext;
    private boolean isClickBle;

    public PhotoAdapter(ArrayList<Photo> imgPaths, Context mContext, boolean isClickBle) {
        this.imgPaths = imgPaths;
        this.mContext = mContext;
        this.isClickBle = isClickBle;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imgPaths.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if (position < imgPaths.size()) {
            return imgPaths.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.photo_gridview_item, null);
            holder = new ViewHolder();
            holder.mItemGridImage = convertView.findViewById(R.id.item_grid_image);
            holder.videoMark = convertView.findViewById(R.id.video_mark);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position < imgPaths.size()) {
            Photo photo = imgPaths.get(position);
            if (!TextUtils.isEmpty(photo.path) && photo.path.contains("mp4")) {
                //该文件路径对应的是取证录像
                holder.videoMark.setVisibility(View.VISIBLE);
            } else {
                holder.videoMark.setVisibility(View.GONE);
            }
            Glide.with(mContext).load(photo.uri).into(holder.mItemGridImage);
        } else {
            holder.videoMark.setVisibility(View.GONE);
            if (isClickBle) {
                Glide.with(mContext).clear(holder.mItemGridImage);
                Glide.with(mContext).load(R.drawable.add_photo).into(holder.mItemGridImage);
                holder.mItemGridImage.setImageResource(R.drawable.add_photo);
            }
        }


        return convertView;
    }

    static class ViewHolder {

        ImageView mItemGridImage;
        TextView videoMark;

    }
}
