package com.livelearn.ignorance.retrofitinterface.book;


import com.livelearn.ignorance.model.bean.book.douban.ActorDetailsBean;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 跟@Path不同,@Query中,指定的是URL中的参数是以键值对的形式出现的,
 * 而在程序中@Query("from") int from则读出URL中from的值,而@Path中,
 * URL中只出现参数的值,不出现键值对,比如:“/users/2011/06/30”
 */
public interface DouBanBook {

    @GET("celebrity/{actorId}")
    Observable<ActorDetailsBean> getActorDetails(@Path("actorId") String actorId);

    /**
     * 根据tag获取图书
     * https://api.douban.com/v2/book/search?tag=日本文学&start=20&count=60
     */
    @GET("search")
    Observable<DouBanBookBean> getBooksByTag(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("search")
    Observable<List<DouBanBookBean>> getBooksByQ(@Query("q") String q, @Query("start") int start, @Query("count") int count);

}
