package com.wordpress.pesanmerpati.customsnackbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wordpress.pesanmerpati.customsnackbar.Model.Items;

import java.util.List;

/**
 * Created by Rohmats on 3/16/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {

    private Context context;
    private List<Items> itemsList;

    public ItemsAdapter(Context context, List<Items> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ItemsAdapter.ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_users,parent,false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemsViewHolder holder, int position) {

        Glide.with(context)
                .load(itemsList.get(position).getAvatar_url())
                .into(holder.mImageView_avatar);
        holder.mTextView_username.setText(itemsList.get(position).getUsername());
        holder.mTextView_html_url.setText(itemsList.get(position).getHtml_url());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView_avatar;
        TextView mTextView_username;
        TextView mTextView_html_url;

        public ItemsViewHolder(View itemView) {
            super(itemView);

            mImageView_avatar = itemView.findViewById(R.id.imageView_avatar);
            mTextView_username = itemView.findViewById(R.id.textView_username);
            mTextView_html_url = itemView.findViewById(R.id.textView_html_url);

        }
    }
}
