package com.example.mymovie.data;

import lombok.Getter;

/**
 * 영화 정보
 */
@Getter
public class ResponseInfo {
    /**
     * 응답 메세지
     */
    private String message;
    /**
     * 응답 코드
     */
    private int code;
    /**
     * 결과 타입
     */
    private String resultType;
}
