package com.example.mymovie.adapter;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 갤러리 정보
 */
@Getter
@AllArgsConstructor
public class GalleryItem {
    /**
     * 썸네일 URL
     */
    private String thumbUrl;
    /**
     * 이미지 타입
     */
    private String type;
    /**
     * 이미지 URL
     */
    private String url;
}
