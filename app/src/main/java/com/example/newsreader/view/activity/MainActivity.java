package com.example.newsreader.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.newsreader.R;
import com.example.newsreader.utils.FragmentUtils;
import com.example.newsreader.view.fragment.NewsListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentUtils.replaceFragment(getSupportFragmentManager(), new NewsListFragment());
    }
}
