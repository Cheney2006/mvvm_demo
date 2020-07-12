package com.cheney.mvvm_demo.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.cheney.mvvm_demo.entity.Gank;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public abstract class GankDao {
    /**
     * 插入数据
     * @param ganks 数据集合
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertGanks(List<Gank> ganks);

    /**
     * 清空整个表
     */
    @Query("DELETE FROM Gank")
    public abstract void truncateTable();

    /**
     * 清空整个表, 再插入数据
     * @param ganks 数据集合
     */
    @Transaction
    public void deleteThenInsert(List<Gank> ganks){
        truncateTable();
        insertGanks(ganks);
    }

    /**
     * 如果结果为空则直接complete
     * @param id 用户id
     */
    @Query("SELECT * FROM Gank where id = :id ")
    public abstract Maybe<Gank> getGank(int id);

    /**
     * 如果为空则抛异常
     * @param id 用户id
     */
    @Query("SELECT * FROM Gank where id = :id ")
    public abstract Maybe<Gank> getGank1(int id);

    /**
     * 取全部数据
     * @return
     */
    @Query("SELECT * FROM Gank")
    public abstract Single<List<Gank>> getAll();
}
