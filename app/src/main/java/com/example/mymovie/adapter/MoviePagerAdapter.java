package com.example.mymovie.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 영화 목록 어댑터
 */
public class MoviePagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> items = new ArrayList<>();

    public MoviePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addItem(Fragment item) {
        items.add(item);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "페이지 " + position;
    }
}
