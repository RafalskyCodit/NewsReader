package com.example.newsreader.adapters;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsreader.R;
import com.example.newsreader.model.Article;
import com.example.newsreader.utils.DateTimeUtils;
import com.example.newsreader.utils.ScrollUtils;
import com.example.newsreader.view.activity.WebActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class NewsListAdapter extends PagedListAdapter<Article, NewsListAdapter.ArticleHolder> {
    private static final DiffUtil.ItemCallback<Article> DIFF_CALLBACK = new DiffUtil.ItemCallback<Article>() {
        @Override
        public boolean areItemsTheSame(@NonNull Article oldArticle, @NonNull Article newArticle) {
            return oldArticle.getUrl().equals(newArticle.getUrl());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Article oldArticle, @NonNull Article newArticle) {
            return oldArticle.equals(newArticle);
        }
    };

    public NewsListAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        return new ArticleHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleHolder articleHolder, int position) {
        articleHolder.bind(getItem(position));
    }

    public class ArticleHolder extends RecyclerView.ViewHolder{
        private ImageView articleImage;
        private TextView articleTitle;
        private TextView articleDescription;
        private TextView articleContent;
        private TextView articleRef;
        private TextView articleAuthor;
        private TextView articleDate;
        private TextView articleSource;

        public ArticleHolder(LayoutInflater inflater, ViewGroup group){
            super(inflater.inflate(R.layout.news_item, group, false));
            init();
            setListeners();
        }

        private void setListeners() {
            articleRef.setOnClickListener(v -> {
                Context context = itemView.getContext();
                context.startActivity(WebActivity.getIntentInstance(context, articleRef.getText().toString()));
            });
        }

        private void init() {
            articleImage = itemView.findViewById(R.id.article_image);
            articleTitle = itemView.findViewById(R.id.article_title);

            articleDescription = itemView.findViewById(R.id.article_description);
            articleContent = itemView.findViewById(R.id.article_content);

            ScrollUtils.enableScroll(articleDescription);
            ScrollUtils.enableScroll(articleContent);

            articleRef = itemView.findViewById(R.id.article_ref);
            articleAuthor = itemView.findViewById(R.id.article_author);
            articleDate = itemView.findViewById(R.id.article_date);
            articleSource = itemView.findViewById(R.id.article_source);
        }

        public void bind(Article article){
            if (article != null){
                String urlToImage = article.getUrlToImage();
                if (!TextUtils.isEmpty(urlToImage)){
                    Picasso.get().load(article.getUrlToImage()).error(R.drawable.error).placeholder(R.drawable.ic_cloud_download).into(articleImage);
                }else{
                    Picasso.get().load(R.drawable.no_image);
                }
                articleTitle.setText(article.getTitle());

                String description = article.getDescription();
                if (!TextUtils.isEmpty(description)){
                    articleDescription.setText(description);
                }else{
                    articleDescription.setText(R.string.no_description);
                }

                String content = article.getContent();
                if (!TextUtils.isEmpty(content)){
                    articleContent.setText(content);
                }else{
                    articleContent.setText(R.string.no_content);
                }

                articleRef.setText(article.getUrl());

                String author = article.getAuthor();
                if (!TextUtils.isEmpty(author)){
                    articleAuthor.setText(author);
                }else {
                    articleAuthor.setText(R.string.unknown_author);
                }

                try {
                    articleDate.setText(DateTimeUtils.convertToUsualFormat(article.getPublishedAt()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String source = article.getSource().getName();
                if (!TextUtils.isEmpty(source)){
                    articleSource.setText(article.getSource().getName());
                }else {
                    articleSource.setText(R.string.unknown_source);
                }
            }else{
                Picasso.get().load(R.drawable.ic_cloud_download).error(R.drawable.error).into(articleImage);
                articleTitle.setText(R.string.loading);
                articleDescription.setText(R.string.loading);
                articleContent.setText(R.string.loading);
                articleRef.setText(R.string.loading);
                articleAuthor.setText(R.string.loading);
                articleDate.setText(R.string.loading);
                articleSource.setText(R.string.loading);
            }
        }
    }
}
