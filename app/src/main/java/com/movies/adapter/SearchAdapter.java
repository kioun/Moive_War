package com.movies.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.movies.Detail;
import com.movies.R;
import com.movies.model.Item;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    private ArrayList<Item> itemArrayList;

    public SearchAdapter(Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {

        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;

        Item item = itemArrayList.get(position);
        searchViewHolder.tv_n_title.setText(Html.fromHtml(item.getTitle()));
        searchViewHolder.rb_n_user_rating.setRating(Float.parseFloat(item.getUserRating()));
        searchViewHolder.tv_n_rating.setText(Html.fromHtml(item.getUserRating()));
        searchViewHolder.tv_n_pubdate.setText(Html.fromHtml(item.getPubDate()));

        Glide.with(context)
                .load(item.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(searchViewHolder.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detail.class);
                intent.putExtra("title", itemArrayList.get(position).getTitle());
                intent.putExtra("rating", itemArrayList.get(position).getUserRating());
                intent.putExtra("director", itemArrayList.get(position).getDirector());
                intent.putExtra("actor", itemArrayList.get(position).getActor());
                intent.putExtra("image", itemArrayList.get(position).getImage());
                intent.putExtra("subtitle", itemArrayList.get(position).getSubtitle());
                intent.putExtra("pubdate", itemArrayList.get(position).getPubDate());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public void clearItems(){
        itemArrayList.clear();
        notifyDataSetChanged();
    }

    public void clearAndAddItems(ArrayList<Item> items){
        itemArrayList.clear();
        itemArrayList.addAll(items);
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_n_poster;
        private TextView tv_n_title;
        private RatingBar rb_n_user_rating;
        private TextView tv_n_rating;
        private TextView tv_n_pubdate;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_n_poster = itemView.findViewById(R.id.iv_n_img);
            tv_n_title = itemView.findViewById(R.id.tv_n_title);
            rb_n_user_rating = itemView.findViewById(R.id.rb_n_user_rating);
            tv_n_rating = itemView.findViewById(R.id.tv_n_rating);
            tv_n_pubdate = itemView.findViewById(R.id.tv_n_pubdate);
        }

        public ImageView getImage(){
            return iv_n_poster;
        }
    }
}
