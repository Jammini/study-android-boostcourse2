package com.example.mymovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovie.R;
import com.example.mymovie.data.Review;

import java.util.ArrayList;

import static com.example.mymovie.utill.FormatTimeString.formatTime;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    OnItemClickListener listener;

    public static interface OnItemClickListener {
        public void onItemClick(ViewHolder viewHolder, View view, int position);
    }

    ArrayList<Review> items = new ArrayList<>();

    public ReviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.review_item_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review item = items.get(position);
        holder.setItem(item);

        holder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Review item) {
        items.add(item);
    }

    public void addItems(ArrayList<Review> items) {
        this.items = items;
    }

    public Review getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView userId;
        TextView reviewUserTime;
        TextView content;
        ImageView imageProfile;
        RatingBar ratingBar;
        OnItemClickListener listener;
        TextView recommend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProfile = itemView.findViewById(R.id.writer_image);
            userId = itemView.findViewById(R.id.userId);
            reviewUserTime = itemView.findViewById(R.id.time);
            content = itemView.findViewById(R.id.contents);
            TextView reportBtn = itemView.findViewById(R.id.reportBtn);
            ratingBar = itemView.findViewById(R.id.rating);
            recommend = itemView.findViewById(R.id.recommend);

            recommend.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Review item = getItem(position);

                int recommendCount = item.getRecommend();
                recommendCount++;
                item.setRecommend(recommendCount);
                this.recommend.setText("추천 " + item.getRecommend());
            });

            reportBtn.setOnClickListener(v -> {
                int position = getAdapterPosition();
                Review item = getItem(position);
                Toast.makeText(context, item.getWriter() + " 사용자 \n신고완료.", Toast.LENGTH_LONG).show();
            });
        }

        /**
         * 프로필 정보 등록
         *
         * @param item 입력 프로필
         */
        public void setItem(Review item) {
            int image = item.getWriter_image();
            if (image == 0) {
                image = R.drawable.user1;
            }
            imageProfile.setImageResource(image);
            userId.setText(item.getWriter());
            reviewUserTime.setText(formatTime((item.getTimestamp())));

            content.setText(item.getContents());
            ratingBar.setRating(item.getRating());
            recommend.setText("추천 " + item.getRecommend());
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }
}
