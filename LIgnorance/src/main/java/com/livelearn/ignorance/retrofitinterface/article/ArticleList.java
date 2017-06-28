package com.livelearn.ignorance.retrofitinterface.article;


import com.livelearn.ignorance.model.bean.article.ArticleListModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ArticleList {
    @GET("data/{Typepath}/{pageCount}/{page}")
    Observable<ArticleListModel> getArticleList(
            @Path("Typepath") String Typepath,
            @Path("pageCount") int pageCount,
            @Path("page") int page
    );
}
