package com.example.mymovie.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mymovie.R;
import com.example.mymovie.activity.AllReviewActivity;
import com.example.mymovie.activity.ReviewWriteActivity;
import com.example.mymovie.adapter.ReviewAdapter;
import com.example.mymovie.data.AppHelper;
import com.example.mymovie.data.MovieInfo;
import com.example.mymovie.data.MovieList;
import com.example.mymovie.data.ResponseInfo;
import com.example.mymovie.data.ReviewInfo;
import com.example.mymovie.data.ReviewList;
import com.google.gson.Gson;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.mymovie.utill.Constants.KEY_AUDIENCE_RATING;
import static com.example.mymovie.utill.Constants.KEY_GRADE;
import static com.example.mymovie.utill.Constants.KEY_INDEX;
import static com.example.mymovie.utill.Constants.KEY_MOVIE_ID;
import static com.example.mymovie.utill.Constants.KEY_TITLE;
import static com.example.mymovie.utill.Constants.KEY_USER_RATING;
import static com.example.mymovie.utill.Constants.REQUEST_CODE;
import static com.example.mymovie.utill.Constants.REQUEST_REVIEW_CODE;

/**
 * 영화 상세정보 화면
 */
public class DetailFragment extends Fragment {
    private ImageView movieDetailPoster;
    private TextView title;
    private ImageView grade;
    private TextView date;
    private TextView genre;
    private TextView ranking;
    private TextView reservationRate;
    private RatingBar userRating;
    private TextView audienceRating;
    private TextView audience;
    private TextView synopsis;
    private TextView director;
    private TextView actor;
    private ReviewAdapter adapter;
    private int likeCount;
    private int dislikeCount;
    private Button likeButton;
    private Button dislikeButton;
    private TextView likeCountTextView;
    private TextView dislikeCountTextView;
    private RecyclerView recyclerView;
    private ArrayList<ReviewInfo> reviewList;
    private int id;
    private String movieTitle;
    private float reviewRating;
    private int movieGrade;
    private float audiRating;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie_detail, container, false);

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getContext());
        }
        movieDetailPoster = rootView.findViewById(R.id.fragment_movie_detail_poster);
        title = rootView.findViewById(R.id.fragment_detail_title);
        grade = rootView.findViewById(R.id.fragment_detail_grade);
        date = rootView.findViewById(R.id.fragment_detail_date);
        genre = rootView.findViewById(R.id.fragment_detail_genre);
        ranking = rootView.findViewById(R.id.fragment_detail_ranking);
        reservationRate = rootView.findViewById(R.id.fragment_reservation_rate);
        userRating = rootView.findViewById(R.id.fragment_detail_user_rating);
        audienceRating = rootView.findViewById(R.id.fragment_detail_audience_rating);
        audience = rootView.findViewById(R.id.fragment_detail_audience);
        synopsis = rootView.findViewById(R.id.fragment_detail_synopsis);
        director = rootView.findViewById(R.id.fragment_detail_director);
        actor = rootView.findViewById(R.id.fragment_detail_actor);
        likeCountTextView = rootView.findViewById(R.id.fragment_detail_likeCount);
        dislikeCountTextView = rootView.findViewById(R.id.fragment_detail_dislikeCount);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        reviewList = new ArrayList<>();
        adapter = new ReviewAdapter(getContext());

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            id = bundle.getInt(KEY_INDEX);
            movieTitle = bundle.getString(KEY_TITLE);
            requestMovieDetail(id);
            requestReview(id);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        likeButton = rootView.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(v -> likeClick());

        dislikeButton = rootView.findViewById(R.id.dislikeButton);
        dislikeButton.setOnClickListener(v -> dislikeClick());

        Button writeOneLine = rootView.findViewById(R.id.writeOneLine);
        writeOneLine.setOnClickListener(v -> showCommentWriteActivity());

        Button allReviewBtn = rootView.findViewById(R.id.allReviewBtn);
        allReviewBtn.setOnClickListener(v -> showAllReviewActivity());

        return rootView;
    }

    /**
     * 영화 상세정보 요청
     *
     * @param id 영화 아이디
     */
    public void requestMovieDetail(int id) {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovie";
        url += "?" + "id=" + id;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                this::processResponse,
                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show()
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    /**
     * 영화 상세정보 처리
     *
     * @param response 영화상세정보
     */
    public void processResponse(String response) {
        Gson gson = new Gson();
        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);

        if (info.getCode() == 200) {
            MovieList movieList = gson.fromJson(response, MovieList.class);
            MovieInfo movieInfo = movieList.result.get(0);
            reviewRating = movieInfo.getReviewer_rating();
            audiRating = movieInfo.getAudience_rating();
            Glide.with(this).load(movieInfo.getThumb()).into(movieDetailPoster);
            title.setText(movieInfo.getTitle());
            setIcon(movieInfo.getGrade());
            date.setText(String.format(getString(R.string.detail_fragment_date), movieInfo.getDate()));
            genre.setText(String.format(getString(R.string.detail_fragment_genre_time), movieInfo.getGenre(), movieInfo.getDuration()));
            ranking.setText(String.format(getString(R.string.detail_fragment_rank), movieInfo.getReservation_grade()));
            reservationRate.setText(String.format(getString(R.string.detail_fragment_rate), movieInfo.getReservation_rate()));
            userRating.setRating(movieInfo.getAudience_rating() / 2);
            audienceRating.setText(String.format(getString(R.string.float_value), movieInfo.getAudience_rating()));
            audience.setText(String.format(getString(R.string.detail_fragment_audience), movieInfo.getAudience()));
            synopsis.setText(movieInfo.getSynopsis());
            director.setText(movieInfo.getDirector());
            actor.setText(movieInfo.getActor());
            likeCountTextView.setText(String.format(getString(R.string.int_value), movieInfo.getLike()));
            dislikeCountTextView.setText(String.format(getString(R.string.int_value), movieInfo.getDislike()));
            likeCount = Integer.parseInt(likeCountTextView.getText().toString());
            dislikeCount = Integer.parseInt(dislikeCountTextView.getText().toString());
        }
    }

    /**
     * 영화 리뷰 요청
     *
     * @param id 해당 영화 아이디
     */
    public void requestReview(int id) {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList?id=" + id + "&limit=2";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                this::processReviewResponse,
                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show());
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    /**
     * 영화 리뷰 처리
     *
     * @param response 영화 리뷰 정보
     */
    public void processReviewResponse(String response) {
        Gson gson = new Gson();
        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);

        if (info.getCode() == 200) {
            ReviewList reviewList = gson.fromJson(response, ReviewList.class);
            this.reviewList.clear();
            this.reviewList.addAll(reviewList.result);
            adapter.addItems(this.reviewList);
            recyclerView.setAdapter(adapter);
        }
    }

    /**
     * 리뷰 작성 액티비티 이동
     */
    public void showCommentWriteActivity() {
        Intent intent = new Intent(getContext(), ReviewWriteActivity.class);
        intent.putExtra(KEY_MOVIE_ID, id);
        intent.putExtra(KEY_TITLE, movieTitle);
        intent.putExtra(KEY_GRADE, movieGrade);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * 리뷰 모두보기 액티비티 이동
     */
    public void showAllReviewActivity() {
        Intent intent = new Intent(getContext(), AllReviewActivity.class);
        intent.putExtra(KEY_MOVIE_ID, id);
        intent.putExtra(KEY_TITLE, movieTitle);
        intent.putExtra(KEY_GRADE, movieGrade);
        intent.putExtra(KEY_USER_RATING, reviewRating);
        intent.putExtra(KEY_AUDIENCE_RATING, ""+audiRating);
        startActivityForResult(intent, REQUEST_REVIEW_CODE);
    }

    /**
     * 받아온 결과 처리
     * @param requestCode 요청 코드
     * @param resultCode  응답 코드
     * @param intent      응답 인텐트
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_REVIEW_CODE) {
                if (intent != null) {
                    requestReview(id);
                }
            } else if (requestCode == REQUEST_CODE) {
                if (intent != null) {
                    requestReview(id);
                }
            }
        }
    }

    /**
     * 등급에 따른 아이콘 설정
     *
     * @param grade 나이 등급
     */
    public void setIcon(int grade) {
        switch (grade) {
            case 12:
                movieGrade = R.drawable.ic_12;
                this.grade.setImageResource(movieGrade);
                break;
            case 15:
                movieGrade = R.drawable.ic_15;
                this.grade.setImageResource(movieGrade);
                break;
            case 19:
                movieGrade = R.drawable.ic_19;
                this.grade.setImageResource(movieGrade);
                break;
            default:
                movieGrade = R.drawable.ic_all;
                this.grade.setImageResource(movieGrade);
        }
    }

    /**
     * 좋아요 버튼 클릭
     */
    public void likeClick() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/increaseLikeDisLike?id=" + id + "&likeyn=";
        if (!likeButton.isSelected() && !dislikeButton.isSelected()) {
            url += "Y";
            likeCount++;
            likeButton.setSelected(true);
            likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        } else if (likeButton.isSelected()) {
            url += "N";
            likeCount--;
            likeButton.setSelected(false);
            likeButton.setBackgroundResource(R.drawable.thumb_up);
        } else if (dislikeButton.isSelected()) {
            likeCount++;
            dislikeCount--;
            likeButton.setSelected(true);
            likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
            dislikeButton.setSelected(false);
            dislikeButton.setBackgroundResource(R.drawable.thumb_down);
            requestProcess(url.replace("likeyn", "dislikeyn") + "N");
            url += "Y";
            try {
                Thread.sleep(500L); // 서버에 거의 동시에 보내면 적용이 안됨.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        likeCountTextView.setText(String.format(getString(R.string.int_value), likeCount));
        dislikeCountTextView.setText(String.format(getString(R.string.int_value), dislikeCount));
        requestProcess(url);
    }

    /**
     * 싫어요 버튼 클릭
     */
    public void dislikeClick() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/increaseLikeDisLike?id=" + id + "&dislikeyn=";

        if (!likeButton.isSelected() && !dislikeButton.isSelected()) {
            url += "Y";
            dislikeCount++;
            dislikeButton.setSelected(true);
            dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        } else if (dislikeButton.isSelected()) {
            url += "N";
            dislikeCount--;
            dislikeButton.setSelected(false);
            dislikeButton.setBackgroundResource(R.drawable.thumb_down);
        } else if (likeButton.isSelected()) {
            dislikeCount++;
            likeCount--;
            dislikeButton.setSelected(true);
            dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
            likeButton.setSelected(false);
            likeButton.setBackgroundResource(R.drawable.thumb_up);
            requestProcess(url.replace("dislikeyn", "likeyn") + "N");
            url += "Y";
            try {
                Thread.sleep(500L); // 서버에 거의 동시에 보내면 적용이 안됨.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        likeCountTextView.setText(String.format(getString(R.string.int_value), likeCount));
        dislikeCountTextView.setText(String.format(getString(R.string.int_value), dislikeCount));
        requestProcess(url);
    }

    /**
     * 좋아요, 싫어요 버튼 요청
     *
     * @param url Y 증가, N 감소
     */
    public void requestProcess(String url) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show()
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }
}
