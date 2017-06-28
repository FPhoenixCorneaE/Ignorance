package com.livelearn.ignorance.retrofitinterface.video;

import com.livelearn.ignorance.model.bean.video.PhotoLithographyDetailsModel;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface PhotoLithography {

    /**
     * 影片详情
     *
     * @param mediaId 影片id
     */
    @GET("videoDetailApi/videoDetail.do")
    Observable<PhotoLithographyDetailsModel> getPhotoLithographyDetailsByMediaId(@Query("mediaId") String mediaId);

    /**
     * 影片分类列表
     *
     * @param catalogId 目录id
     * @param pnum      页数
     */
    @GET("columns/getVideoList.do")
    Observable<PhotoLithographyTypeListModel> getPhotoLithographyTypeList(@Query("catalogId") String catalogId, @Query("pnum") int pnum);

    /**
     * 影片搜索
     *
     * @param keyword 关键字
     * @param pnum    页数
     */
    @POST("searchKeyWordApi/getVideoListByKeyWord.do")
    Observable<PhotoLithographyDetailsModel> getPhotoLithographyVideoListByKeyword(@Query("keyword") String keyword, @Query("pnum") int pnum);
}
