package com.example.mymovie.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 영화 상세 정보
 */
@Getter
@Setter
@AllArgsConstructor
public class MovieInfo {
    /**
     * 영화 제목
     */
    private String title;
    /**
     * 영화 아이디
     */
    private int id;
    /**
     * 개봉일
     */
    private String date;
    /**
     * 사용자 평가
     */
    private float user_rating;
    /**
     * 청중 평가
     */
    private float audience_rating;
    /**
     * 리뷰어 평가
     */
    private float reviewer_rating;
    /**
     * 예매율
     */
    private float reservation_rate;
    /**
     * 예매등급
     */
    private int reservation_grade;
    /**
     * 예매 제한 나이
     */
    private int grade;
    /**
     * 썸네일
     */
    private String thumb;
    /**
     * 포스터
     */
    private String image;
    /**
     * 영화 장면 포토
     */
    private String photos;
    /**
     * 영화 클립
     */
    private String videos;
    /**
     * 외부링크
     */
    private String outlinks;
    /**
     * 영화 장르
     */
    private String genre;
    /**
     * 기간
     */
    private int duration;
    /**
     * 총 청중
     */
    private int audience;
    /**
     * 영화설명
     */
    private String synopsis;
    /**
     * 감독
     */
    private String director;
    /**
     * 배우
     */
    private String actor;
    /**
     * 좋아요 수
     */
    private int like;
    /**
     * 싫어요 수
     */
    private int dislike;
}
