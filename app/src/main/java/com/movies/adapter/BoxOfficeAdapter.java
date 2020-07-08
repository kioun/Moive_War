package com.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.movies.R;
import com.movies.adapter.SearchAdapter.SearchViewHolder;
import com.movies.model.DailyBoxOfficeList;
import com.movies.model.Item;

import java.util.ArrayList;
import java.util.List;

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.BoxOfficeViewHolder> {

    private List<DailyBoxOfficeList> dailyBoxOfficeLists;
    private Context context;

    public BoxOfficeAdapter(List<DailyBoxOfficeList> dailyBoxOfficeLists, Context context) {
        this.dailyBoxOfficeLists = dailyBoxOfficeLists;
        this.context = context;
    }

    @NonNull
    @Override
    public BoxOfficeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent, false);
        return new BoxOfficeViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxOfficeViewHolder holder, int position) {
        BoxOfficeViewHolder boxOfficeViewHolder = (BoxOfficeViewHolder) holder;
        DailyBoxOfficeList dailyBoxOfficeList = dailyBoxOfficeLists.get(position);
        boxOfficeViewHolder.tv_rank.setText(dailyBoxOfficeList.getRank()+"위");
        boxOfficeViewHolder.tv_moveName.setText(dailyBoxOfficeList.getMovieNm());
        boxOfficeViewHolder.tv_moveDate.setText(dailyBoxOfficeList.getOpenDt());
        boxOfficeViewHolder.tv_salesAcc.setText(dailyBoxOfficeList.getSalesAcc()+"원");
        boxOfficeViewHolder.tv_audiAcc.setText(dailyBoxOfficeList.getAudiAcc()+"명");

    }

    @Override
    public int getItemCount() {
        return dailyBoxOfficeLists.size();
    }

    public class BoxOfficeViewHolder extends RecyclerView.ViewHolder {

        TextView tv_rank;
        TextView tv_moveName;
        TextView tv_moveDate;
        TextView tv_salesAcc;
        TextView tv_audiAcc;

        public BoxOfficeViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_rank = itemView.findViewById(R.id.tv_rank);
            tv_moveName = itemView.findViewById(R.id.tv_movietitle);
            tv_moveDate = itemView.findViewById(R.id.tv_moviedate);
            tv_salesAcc = itemView.findViewById(R.id.tv_salesAcc);
            tv_audiAcc = itemView.findViewById(R.id.tv_audiAcc);
        }

    }

}
