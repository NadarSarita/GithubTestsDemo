package com.app.idnbin.MarketAnalysis;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.R;
import com.app.idnbin.util.ApiCall;
import com.app.idnbin.util.ApiIterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    RecyclerView news_view;
    NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        news_view = view.findViewById(R.id.news_view);
        getdata();
        return view;
    }

    private void getdata() {
        ApiIterface apiIterface = ApiCall.getForexnew().create(ApiIterface.class);
        Call<Newsfeed> call = apiIterface.getnewsdata();
        call.enqueue(new Callback<Newsfeed>() {
            @Override
            public void onResponse(Call<Newsfeed> call, Response<Newsfeed> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        Newsfeed newsfeed = response.body();
                        news_view.setLayoutManager(new LinearLayoutManager(getContext()));
                        newsAdapter = new NewsAdapter(getContext(), newsfeed, newsfeed.getItemCounts().size());
                        news_view.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();

                    }
                }

            }

            @Override
            public void onFailure(Call<Newsfeed> call, Throwable t) {
                Log.d("", "ddaataaaddaaaaaaaaaaaaaaaaa" + t);

            }
        });

    }

}
