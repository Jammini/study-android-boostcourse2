package com.example.mymovie.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mymovie.utill.Constants;
import com.example.mymovie.R;
import com.example.mymovie.adapter.MoviePagerAdapter;
import com.example.mymovie.data.AppHelper;
import com.example.mymovie.data.MovieInfo;
import com.example.mymovie.data.MovieList;
import com.example.mymovie.data.ResponseInfo;
import com.google.gson.Gson;

/**
 * 영화 리스트 화면
 */
public class ListFragment extends Fragment {
    private MoviePagerAdapter movieAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie_list, container, false);

        ViewPager pager = rootView.findViewById(R.id.fragment_movie_viewpager);
        pager.setOffscreenPageLimit(3);
        pager.setClipToPadding(false);
        pager.setPadding(70, 0, 70, 0);
        pager.setPageMargin(getResources().getDisplayMetrics().widthPixels / -9);

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getContext());
        }
        movieAdapter = new MoviePagerAdapter(getFragmentManager());
        requestMovieList();
        pager.setAdapter(movieAdapter);

        return rootView;
    }

    /**
     * 영화 리스트 요청
     */
    public void requestMovieList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> processResponse(response),
                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show()
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    /**
     * 요청 받은 데이터 처리
     *
     * @param response 응답 데이터
     */
    public void processResponse(String response) {
        Gson gson = new Gson();
        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);

        if (info.getCode() == 200) {
            MovieList movieList = gson.fromJson(response, MovieList.class);

            for (int i = 0; i < movieList.result.size(); i++) {
                MovieInfo movieInfo = movieList.result.get(i);
                movieAdapter.addItem(setData(i + 1, movieInfo.getId(), movieInfo.getImage(), movieInfo.getTitle(), movieInfo.getReservation_rate(), movieInfo.getGrade(), movieInfo.getDate()));
                movieAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * PosterFragment로 넘겨준다.
     *
     * @param index    인덱스
     * @param id       영화 아이디
     * @param imageUrl 이미지
     * @param title    제목
     * @param rate     예매율
     * @param grade    관람등급
     * @param date     개봉일
     * @return PosterFragment 객체
     */
    public PosterFragment setData(int index, int id, String imageUrl, String title, float rate, int grade, String date) {
        PosterFragment fragment = new PosterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_INDEX, index);
        bundle.putInt(Constants.KEY_MOVIE_ID, id);
        bundle.putString(Constants.KEY_IMAGE_URL, imageUrl);
        bundle.putString(Constants.KEY_TITLE, title);
        bundle.putFloat(Constants.KEY_RATE, rate);
        bundle.putInt(Constants.KEY_GRADE, grade);
        bundle.putString(Constants.KEY_DATE, date);
        fragment.setArguments(bundle);
        return fragment;
    }
}
