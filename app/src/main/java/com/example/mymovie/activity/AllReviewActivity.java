package com.example.mymovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mymovie.R;
import com.example.mymovie.adapter.ReviewAdapter;
import com.example.mymovie.data.AppHelper;
import com.example.mymovie.data.ResponseInfo;
import com.example.mymovie.data.ReviewInfo;
import com.example.mymovie.data.ReviewList;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.mymovie.utill.Constants.KEY_AUDIENCE_RATING;
import static com.example.mymovie.utill.Constants.KEY_GRADE;
import static com.example.mymovie.utill.Constants.KEY_MOVIE_ID;
import static com.example.mymovie.utill.Constants.KEY_TITLE;
import static com.example.mymovie.utill.Constants.KEY_USER_RATING;
import static com.example.mymovie.utill.Constants.REQUEST_CODE;

/**
 * 한줄평 모두 보기 화면
 */
public class AllReviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private ArrayList<ReviewInfo> reviewList;
    private TextView allReviewTitle;
    private ImageView allReviewGrade;
    private RatingBar ratingBar;
    private TextView reviewRate;
    private int id;
    private String movieTitle;
    private int movieGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_review);
        // 앱바 제목 텍스트 변경
        ActionBar ab = getSupportActionBar();
        ab.setTitle("한줄평 목록");

        allReviewTitle = findViewById(R.id.activity_all_review_title);
        allReviewGrade = findViewById(R.id.activity_all_review_grade);
        ratingBar = findViewById(R.id.activity_all_review_rating);
        reviewRate = findViewById(R.id.activity_all_review_review_rate);
        getData();
        requestReview(id);

        Button button = findViewById(R.id.writeOneLine);
        button.setOnClickListener(v -> showCommentWriteActivity());

        reviewList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ReviewAdapter(getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 영화 정보를 받아온다.
     */
    public void getData() {
        Intent intent = getIntent();
        id = intent.getIntExtra(KEY_MOVIE_ID, 1);
        movieGrade = intent.getIntExtra(KEY_GRADE, 15);
        movieTitle = intent.getStringExtra(KEY_TITLE);
        allReviewTitle.setText(intent.getStringExtra(KEY_TITLE));
        allReviewGrade.setImageResource(intent.getIntExtra(KEY_GRADE, 15));
        ratingBar.setRating(intent.getFloatExtra(KEY_USER_RATING, 0.0f));
        reviewRate.setText(intent.getStringExtra(KEY_AUDIENCE_RATING));
    }

    /**
     * Back Key 사용처리
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 받아 온 결과 처리
     *
     * @param requestCode 요청 코드
     * @param resultCode  응답 코드
     * @param intent      응답 인텐트
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (intent != null) {
                    requestReview(id);
                }
            }
        }
    }

    /**
     * 영화 리뷰 요청
     *
     * @param id 영화 아이디
     */
    public void requestReview(int id) {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList?id=" + id;
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                this::processReviewResponse,
                error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    /**
     * 영화 리뷰 담기
     *
     * @param response 해당 영화 리뷰 응답
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
        Intent intent = new Intent(this, ReviewWriteActivity.class);
        intent.putExtra(KEY_MOVIE_ID, id);
        intent.putExtra(KEY_TITLE, movieTitle);
        intent.putExtra(KEY_GRADE, movieGrade);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
