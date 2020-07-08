package com.movies.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.movies.fragment.FragBoxoffice;
import com.movies.fragment.FragSearch;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    //프래그먼트 교체를 보여주는 처리
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragBoxoffice.newInstence();
            case 1:
                return FragSearch.newInstence();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    //  상단의 탭 레이아웃 텍스트 선언
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "박스오피스";
            case 1:
                return "영화 검색";
            default:
                return null;
        }
    }




}
