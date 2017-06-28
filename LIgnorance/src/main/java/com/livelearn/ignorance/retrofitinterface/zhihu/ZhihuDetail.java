package com.livelearn.ignorance.retrofitinterface.zhihu;


import com.livelearn.ignorance.model.bean.zhihu.ZhihuDetailModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ZhihuDetail {
    @GET("{path}")
    Observable<ZhihuDetailModel> getZhihuDetail(
            @Path("path") int path
    );
}
