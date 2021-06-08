package com.example.mymovie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymovie.R;
import com.example.mymovie.utill.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<GalleryItem> items = new ArrayList<GalleryItem>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public GalleryItem getItem(int position) {
        return items.get(position);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setItem(items.get(position));
        holder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<GalleryItem> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public void addItem(GalleryItem item) {
        items.add(item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumb;
        private ImageView playIc;

        OnItemClickListener listener;

        public ViewHolder(View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.thumb);
            playIc = itemView.findViewById(R.id.play_ic);

            thumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

        void setItem(GalleryItem item) {
            Glide.with(itemView.getContext()).load(item.getThumbUrl()).into(thumb);
            if (item.getType().equals(Constants.GALLERY_TYPE_MOVIE)) {
                playIc.setVisibility(View.VISIBLE);
            } else {
                playIc.setVisibility(View.INVISIBLE);
            }
        }

        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

    }
}
