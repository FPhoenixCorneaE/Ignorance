package com.livelearn.ignorance.retrofitinterface.book;

import com.livelearn.ignorance.model.bean.book.BookRecommendModel;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * API接口
 * 因为使用RxCache作为缓存策略 所以这里不需要写缓存信息
 */
public interface LongTimeBook {

    //获得推荐banner以及书籍数据
    @GET("freebook/home.json")
    Observable<BookRecommendModel> getBookRecommend();

    //获取书库分类信息
    @GET("freebook/typeconfigs.json")
    Observable<List<BookTypeModel>> getBookTypes();

    //获得搜索标签
    @GET("freebook/search_lable.json")
    Observable<List<String>> getSearchBookLabel();
}
