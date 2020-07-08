package com.movies.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.movies.R;
import com.movies.adapter.SearchAdapter;
import com.movies.model.Item;
import com.movies.model.Movie;
import com.movies.service.SearchInterface;
import com.movies.service.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private SearchAdapter searchAdapter;

    private EditText et_n_keyword;
    private Button btn_n_search;

    private InputMethodManager inputMethodManager;

    public Search(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_search, container, false);
        setupRecyclerView(root);
        setupSearchView(root);
        return root;
    }

    private void setupSearchView(View root) {
        et_n_keyword = root.findViewById(R.id.et_n_keyword);
        btn_n_search = root.findViewById(R.id.btn_n_search);
        btn_n_search.setOnClickListener(this);
        inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void setupRecyclerView(View root) {
        recyclerView = root.findViewById(R.id.rv_search_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Item> movies = new ArrayList<>();
        searchAdapter = new SearchAdapter(getContext(), movies);
        recyclerView.setAdapter(searchAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_n_search:
                hideKeyboard();
                startSearch(et_n_keyword.getText().toString());
                break;
        }
    }

    private void startSearch(String title) {
        if (title.isEmpty()) {
            showEmptyFielMessage();
        } else {
            layoutManager.scrollToPosition(0);
            getMovies(title);
        }
    }

    private void getMovies(String title) {
        SearchInterface searchInterface = ServiceGenerator.createService(SearchInterface.class);

        retrofit2.Call<Movie> call = searchInterface.getMovies(title, 100, 1);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    ArrayList<Item> movies = new ArrayList(response.body().getItems());
                    if (movies.size() == 0){
                        searchAdapter.clearItems();
                        showNotFoundMessage(title);
                    } else {
                        searchAdapter.clearAndAddItems(movies);
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    private void showNotFoundMessage(String title) {
        Toast.makeText(getContext(), "찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
    }

    private void showEmptyFielMessage() {
        Toast.makeText(getContext(), "검색어를 입력해주세요", Toast.LENGTH_SHORT).show();
    }

    private void hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(recyclerView.getWindowToken(),0);
    }
}
