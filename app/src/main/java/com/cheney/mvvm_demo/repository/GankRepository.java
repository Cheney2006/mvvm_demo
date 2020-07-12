package com.cheney.mvvm_demo.repository;

import com.cheney.mvvm_demo.api.GankApi;
import com.cheney.mvvm_demo.bean.TodayGank;
import com.cheney.mvvm_demo.constants.Constants;
import com.cheney.mvvm_demo.db.GankDao;
import com.cheney.mvvm_demo.entity.Gank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class GankRepository {
    /**
     * 生成API 实例
     */
    private final GankApi apiService;
    /**
     * 数据库 实例
     */
    private final GankDao dao;

    @Inject
    GankRepository(GankApi apiService, GankDao dao) {
        this.apiService = apiService;
        this.dao = dao;
    }

    public Single<TodayGank> getTodayGanks() {
        return Single.zip( // 这里是一次性发所有请求, 也可以一条一条发
                apiService.getByCategory(Constants.Api.CATEGORY_Girl, Constants.Api.CATEGORY_Girl, 10, 1).subscribeOn(Schedulers.io()), // 福利
                apiService.getByCategory(Constants.Api.CATEGORY_GAN_HUO, Constants.Api.CATEGORY_ANDROID, 20, 1).subscribeOn(Schedulers.io()), // Android
                apiService.getByCategory(Constants.Api.CATEGORY_GAN_HUO, Constants.Api.CATEGORY_FRONT_END, 20, 1).subscribeOn(Schedulers.io()), // 前端
                apiService.getByCategory(Constants.Api.CATEGORY_GAN_HUO, Constants.Api.CATEGORY_FLUTTER, 1, 1).subscribeOn(Schedulers.io()), // 视频
                (t1, t2, t3, t4) -> {
                    if (t1.isOk() && t2.isOk() && t3.isOk() && t4.isOk()) {
                        return new TodayGank(t1.getData(), t2.getData(), t3.getData(), t4.getData());
                    } else {
                        throw new IOException("Get Error From Server");
                    }
                })
//            .subscribeOn(Schedulers.io()) // 网络线程执行上面的(没必要)
                .observeOn(Schedulers.single()) // db线程执行下面的
                .doOnSuccess(todayGanks -> {
                            List willInsert = new ArrayList();

                            willInsert.addAll(todayGanks.getFuli());
                            willInsert.addAll(todayGanks.getAndroid());
                            willInsert.addAll(todayGanks.getFrondEnd());
                            willInsert.addAll(todayGanks.getVideo());
                            dao.deleteThenInsert(willInsert);// 执行成功, 写入db
                        }
                );
    }

    public Single<Gank> getGankById(String id) {
        return apiService.getById(id).map(result -> {
            if (result.isOk()) {
                return result.getData();
            } else {
                throw new IOException("Get Error From Server");
            }
        }).subscribeOn(Schedulers.io());
    }

}
