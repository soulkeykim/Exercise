package com.soulkey.androidexercise.Adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.soulkey.androidexercise.Common.ECGlobal;
import com.soulkey.androidexercise.Common.ECMethod;
import com.soulkey.androidexercise.R;
import com.soulkey.androidexercise.Struct.ECRow;

import java.util.List;

public class ECRecyclerAdapter extends RecyclerView.Adapter<ECRecyclerAdapter.ViewHolder>{
    private List<ECRow> items;
    private int itemLayout;

    private ImageLoader imageLoader;
    private DisplayImageOptions defaultOptions;

    public ECRecyclerAdapter(List<ECRow> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;

        initialImageLoader();
    }

    private void initialImageLoader()    {
        defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(ECGlobal.getCurrentActivity()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ECRow item = items.get(position);

        holder.title.setText(item.title);
        holder.description.setText(item.description);

        //if imageHref is null, do not display imageview
        if(ECMethod.checkNull(item.imageHref)) {
            holder.image.setVisibility(View.GONE);
        }
        else {
            holder.image.setVisibility(View.VISIBLE);
            if(item.conError)
                holder.image.setImageResource(R.drawable.notification_error);
            else {
                holder.image.setImageResource(R.drawable.ic_launcher);
                final ImageView imageView = holder.image;
                imageLoader.displayImage(item.imageHref, holder.image, defaultOptions, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        imageView.setImageResource(R.drawable.notification_error);
                        item.conError = true;
                        item.save();
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(ECRow item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void updateList(List<ECRow> updateItems)
    {
        items =  updateItems;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.titleView);
            description = (TextView) itemView.findViewById(R.id.descView);
        }
    }
}
