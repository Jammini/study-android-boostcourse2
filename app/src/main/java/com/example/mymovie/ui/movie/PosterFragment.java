package com.example.mymovie.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mymovie.R;
import com.example.mymovie.utill.Constants;

/**
 * 영화 목록 화면
 */
public class PosterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_poster, container, false);

        ImageView posterImg = rootView.findViewById(R.id.fragment_poster_image);
        TextView posterTitle = rootView.findViewById(R.id.fragment_poster_title);
        TextView reservationRate = rootView.findViewById(R.id.fragment_poster_reservation_rate);
        TextView grade = rootView.findViewById(R.id.fragment_poster_grade);

        Bundle bundle = getArguments();

        if (getArguments() != null) {
            Glide.with(this).load(bundle.getString(Constants.KEY_IMAGE_URL)).into(posterImg);
            posterTitle.setText(String.format(getString(R.string.list_fragment_title), bundle.getInt(Constants.KEY_INDEX), bundle.getString(Constants.KEY_TITLE)));
            reservationRate.setText(String.format(getString(R.string.list_fragment_rate), bundle.getFloat(Constants.KEY_RATE)));
            grade.setText(String.format(getString(R.string.list_fragment_grade), bundle.getInt(Constants.KEY_GRADE)));
        }
        DetailFragment detailFragment = new DetailFragment();
        Button button = rootView.findViewById(R.id.detailButton);

        button.setOnClickListener(v -> {
            int id = bundle.getInt(Constants.KEY_MOVIE_ID);
            bundle.putInt(Constants.KEY_MOVIE_ID, id);
            detailFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
        });
        return rootView;
    }
}
