package com.example.mymovie.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
/**
 * 리뷰 정보
 */
public class Review {
    /**
     * 아이디
     */
    @PrimaryKey
    private int id;
    /**
     * 작성자
     */
    private String writer;
    /**
     * 작성자 프로필
     */
    private int writer_image;
    /**
     * 평점
     */
    private float rating;
    /**
     * 시간
     */
    private String time;
    /**
     * 타임스탬프
     */
    private long timestamp;
    /**
     * 내용
     */
    private String contents;
    /**
     * 추천수
     */
    private int recommend;
}
