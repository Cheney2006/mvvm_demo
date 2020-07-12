package com.cheney.mvvm_demo.api;

import com.cheney.mvvm_demo.bean.Result;
import com.cheney.mvvm_demo.entity.Gank;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankApi {



    /**
     * 获取某一个分类下的数据, 可以返回Observable, Flowable, Maybe, 建议返回Single
     * 网络请求是一个Request对应一个 Response，不会出现背压情况，所以不考虑 Flowable；网络请求是一个Request对应一个 Response，不是一个连续的事件流，所以在 onNext 被调用之后，onComplete 就会被马上调用，所以只需要 onNext 和 onComplete 其中一个就够了，不考虑 Observable、Maybe ；
     *
     * @param category 分类名
     * @param type     类型
     * @param count    请求数量
     * @param page     请求的页面序号
     */
//    @GET("data/category/{category}/page/{page}/count/{count}")
    @GET("data/category/{category}/type/{type}/page/{page}/count/{count}")
//    @GET("data/{category}/{count}/{page}")
    Single<Result<List<Gank>>> getByCategory(
            @Path("category") String category,
            @Path("type") String type,
            @Path("count") int count,
            @Path("page") int page);


    @GET("post/{id}")
    Single<Result<Gank>> getById(@Path("id") String id);
}
