package com.example.newsreader.view.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsreader.R;
import com.example.newsreader.adapters.NewsListAdapter;
import com.example.newsreader.utils.FragmentUtils;
import com.example.newsreader.viewmodel.MainViewModel;
import com.github.clans.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {
    private RecyclerView resultList;
    private FloatingActionButton filterTopFab;
    private FloatingActionButton filterAllFab;

    private MainViewModel model;
    private NewsListAdapter adapter;

    public NewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setListeners();
    }

    private void setListeners() {
        FragmentManager fm = getFragmentManager();
        filterTopFab.setOnClickListener(view -> FragmentUtils.replaceFragment(fm, new TopArticleFilterFragment()));

        filterAllFab.setOnClickListener(view -> FragmentUtils.replaceFragment(fm, new GeneralFilterFragment()));

        model.getPagedList().observe(this, pagedList -> adapter.submitList(pagedList));
    }

    private void init(View view) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        resultList = view.findViewById(R.id.result_list);
        resultList.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NewsListAdapter();
        resultList.setAdapter(adapter);

        filterAllFab = view.findViewById(R.id.filter_all_fab);
        filterTopFab = view.findViewById(R.id.filter_top_fab);
    }
}
