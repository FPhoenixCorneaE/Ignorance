package com.livelearn.ignorance.retrofitinterface.image;

import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureModel;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureShowModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TianGouPrettyPicture {
    //图片列表
    @GET("/tnfs/api/list")
    Observable<TianGouPrettyPictureModel>
    getPrettyPictureList(@Query("id") int type, @Query("page") int page, @Query("rows") int rows);

    //图片展示
    @GET("/tnfs/api/show")
    Observable<TianGouPrettyPictureShowModel>
    getPrettyPictureShow(@Query("id") int id);
}
