package com.example.mymovie.utill;

/**
 * 시간 변환
 */
public class FormatTimeString {
    private static final int SEC = 60;
    private static final int MIN = 60;
    private static final int HOUR = 24;
    private static final int DAY = 30;
    private static final int MONTH = 12;

    public static String formatTime(long regTime) {
        regTime *= 1000L;
        long curTime = System.currentTimeMillis();
        long diffTime = (curTime - regTime) / 1000;
        String msg;

        if (diffTime < FormatTimeString.SEC) {
            msg = "방금";
        } else if ((diffTime /= FormatTimeString.SEC) < FormatTimeString.MIN) {
            msg = diffTime + "분전";
        } else if ((diffTime /= FormatTimeString.MIN) < FormatTimeString.HOUR) {
            msg = (diffTime) + "시간전";
        } else if ((diffTime /= FormatTimeString.HOUR) < FormatTimeString.DAY) {
            msg = (diffTime) + "일전";
        } else if ((diffTime /= FormatTimeString.DAY) < FormatTimeString.MONTH) {
            msg = (diffTime) + "달전";
        } else {
            msg = (diffTime) + "년전";
        }
        return msg;
    }
}
