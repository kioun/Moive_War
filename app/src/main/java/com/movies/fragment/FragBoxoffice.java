package com.movies.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movies.R;

import com.movies.adapter.BoxOfficeAdapter;
import com.movies.model.BoxOfficeResult;
import com.movies.model.DailyBoxOfficeList;
import com.movies.model.Result;
import com.movies.service.MovieInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragBoxoffice extends Fragment {

    //영화진흥원 api
    final String BASE_URL = "http://www.kobis.or.kr";
    private Retrofit retrofit;
    private MovieInterface movieInterface;
    RecyclerView boxoffice_recycler;

    String API_KEY = "APIKEY";

    List<DailyBoxOfficeList> dailyBoxOfficeLists = new ArrayList<>();
    BoxOfficeAdapter boxOfficeAdapter;


    //어제 날짜
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    String time = simpleDateFormat.format(date.getTime()+(1000*60*60*24*-1));

    //어댑터와 통신하기 위함
    public static FragBoxoffice newInstence(){
        FragBoxoffice fragBoxoffice = new FragBoxoffice();
        return fragBoxoffice;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boxoffice,container,false);

        boxoffice_recycler = view.findViewById(R.id.boxoffice_recycler);
        // 구분선 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        boxoffice_recycler.addItemDecoration(dividerItemDecoration);


        //retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieInterface = retrofit.create(MovieInterface.class);

        movieInterface.getBoxOffice(API_KEY,time).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()){
                    Log.d("retro", 1+"");
                    Result result = response.body();
                    BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();

                    List<DailyBoxOfficeList> dailyBoxOfficeListList2 = boxOfficeResult.getDailyBoxOfficeList();
                    for (DailyBoxOfficeList dailyBoxOffice : dailyBoxOfficeListList2){
                        dailyBoxOfficeLists.add(dailyBoxOffice);
                    }

                    boxOfficeAdapter = new BoxOfficeAdapter(dailyBoxOfficeLists, getContext());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                    boxoffice_recycler.setLayoutManager(linearLayoutManager);
                    boxoffice_recycler.setAdapter(boxOfficeAdapter);
                }else {
                    Log.d("retro", 2+"Error");
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });

        return view;
    }
}
