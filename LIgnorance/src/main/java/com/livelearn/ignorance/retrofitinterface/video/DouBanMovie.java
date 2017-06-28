package com.livelearn.ignorance.retrofitinterface.video;


import com.livelearn.ignorance.model.bean.video.MovieDetailsBean;
import com.livelearn.ignorance.model.bean.video.MoviesBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface DouBanMovie {
    /**
     * 根据tag获取电影
     * https://api.douban.com/v2/movie/search?tag=喜剧&start=20
     */

    @GET("search")
    Observable<List<MoviesBean>> getMovieByTag
    (@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    /**
     * 获取正在热映的电影
     * https://api.douban.com/v2/movie/in_theaters
     */
    @GET("in_theaters")
    Observable<List<MoviesBean>> getMovieByHot
    (@Query("start") int start, @Query("count") int count);

    /**
     * 即将上映的电影
     * https://api.douban.com/v2/movie/coming_soon
     */
    @GET("coming_soon")
    Observable<List<MoviesBean>> getMovieByNew
    (@Query("start") int start, @Query("count") int count);

    /**
     * 电影top250
     * https://api.douban.com/v2/movie/top250?start=20&count=60
     */
    @GET("top250")
    Observable<List<MoviesBean>> getMovieByTop
    (@Query("start") int start, @Query("count") int count);

    /**
     * 根据关键字查找电影
     * https://api.douban.com/v2/movie/search?q=无敌&start=20&count=5
     */
    @GET("search")
    Observable<List<MoviesBean>> getMovieByQ
    (@Query("q") String q, @Query("start") int start, @Query("count") int count);

    @GET("subject/{MovieId}")
    Observable<MovieDetailsBean> getMovieDetails
            (@Path("MovieId") String MovieId);

}
