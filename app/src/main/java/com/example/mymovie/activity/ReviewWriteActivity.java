package com.example.mymovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.mymovie.R;
import com.example.mymovie.data.AppHelper;

import static com.example.mymovie.utill.Constants.KEY_GRADE;
import static com.example.mymovie.utill.Constants.KEY_MOVIE_ID;
import static com.example.mymovie.utill.Constants.KEY_TITLE;

/**
 * 한줄평 작성 화면
 */
public class ReviewWriteActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private EditText contentsInput;
    private TextView reviewMovieTitle;
    private ImageView reviewGrade;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("한줄평 작성");

        reviewMovieTitle = findViewById(R.id.activity_review_title);
        reviewGrade = findViewById(R.id.activity_review_grade);
        ratingBar = findViewById(R.id.activity_review_rating);
        contentsInput = findViewById(R.id.activity_contents_input);
        getData();

        Button finishButton = findViewById(R.id.activity_review_finishButton);
        finishButton.setOnClickListener(v -> finish());

        Button saveButton = findViewById(R.id.activity_review_saveButton);
        saveButton.setOnClickListener(v -> returnToMain());
    }

    /**
     * 리뷰 데이터 가져오기
     */
    public void getData() {
        Intent intent = getIntent();
        id = intent.getIntExtra(KEY_MOVIE_ID, 1);
        reviewMovieTitle.setText(intent.getStringExtra(KEY_TITLE));
        reviewGrade.setImageResource(intent.getIntExtra(KEY_GRADE, 15));
    }

    /**
     * 저장하기 버튼 클릭
     */
    public void returnToMain() {
        String contents = contentsInput.getText().toString();
        float rating = ratingBar.getRating();
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/createComment?id=" + id + "&writer=" + "테스터" + "&rating=" + rating + "&contents=" + contents;

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);

        finish();
    }
}
