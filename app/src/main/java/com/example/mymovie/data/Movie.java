package com.example.mymovie.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 영화 상세 정보
 */
@Getter
@Setter
@AllArgsConstructor
@Entity(tableName = "Movies")
public class Movie {
    /**
     * 영화 아이디
     */
    @PrimaryKey
    private int id;
    /**
     * 영화 제목
     */
    private String title;
    /**
     * 예매율
     */
    private float reservation_rate;
    /**
     * 예매순위
     */
    private int reservation_grade;
    /**
     * 관람등급
     */
    private int grade;
    /**
     * 개봉일
     */
    private String date;
    /**
     * 영화 장르
     */
    private String genre;
    /**
     * 러닝타임
     */
    private int duration;
    /**
     * 좋아요
     */
    private int like;
    /**
     * 싫어요
     */
    private int dislike;
    /**
     * 평점
     */
    private float audience_rating;
    /**
     * 누적 관객수
     */
    private int audience;
    /**
     * 줄거리
     */
    private String synopsis;
    /**
     * 감독
     */
    private String director;
    /**
     * 출연진
     */
    private String actor;
    /**
     * 포스터 URL
     */
    private String image;
    /**
     * 썸네일 URL
     */
    private String thumb;
}
