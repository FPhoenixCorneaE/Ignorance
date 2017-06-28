package com.livelearn.ignorance.retrofitinterface.zhihu;


import com.livelearn.ignorance.model.bean.zhihu.ZhihuListModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ZhihuList {
    @GET("{path}")
    Observable<ZhihuListModel> getZhihuList(
            @Path("path") String path
    );
}
