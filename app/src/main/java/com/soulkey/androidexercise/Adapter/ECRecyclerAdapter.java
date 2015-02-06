package com.soulkey.androidexercise.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soulkey.androidexercise.R;
import com.soulkey.androidexercise.Struct.ECRow;

import java.util.List;

public class ECRecyclerAdapter extends RecyclerView.Adapter<ECRecyclerAdapter.ViewHolder>{
    private List<ECRow> items;
    private int itemLayout;

    public ECRecyclerAdapter(List<ECRow> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
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
