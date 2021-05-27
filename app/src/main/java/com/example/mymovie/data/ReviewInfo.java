package com.example.mymovie.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 리뷰 상세 정보
 */
@Getter
@Setter
@AllArgsConstructor
public class ReviewInfo {
    /**
     * 아이디
     */
    private int id;
    /**
     * 작성자
     */
    private String writer;
    /**
     * 영화 아이디
     */
    private int movieId;
    /**
     * 작성자 프로필
     */
    private int writer_image;
    /**
     * 등록시간
     */
    private String time;
    /**
     * 시간
     */
    private long timestamp;
    /**
     * 평점
     */
    private float rating;
    /**
     * 내용
     */
    private String contents;
    /**
     * 추천 수
     */
    private int recommend;
}
