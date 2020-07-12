package com.cheney.mvvm_demo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cheney.mvvm_demo.entity.Gank;

@Database(entities = {Gank.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {


    /**
     * 获取Gank表Dao
     *
     * @return
     */
    public abstract GankDao getGankDao();

}
