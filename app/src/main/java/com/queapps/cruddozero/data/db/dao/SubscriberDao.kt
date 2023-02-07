package com.queapps.cruddozero.data.db.dao

import androidx.room.*
import com.queapps.cruddozero.data.db.entity.SubscriberEntity

@Dao
interface SubscriberDao {
    @Insert
    suspend fun insert(subscriber: SubscriberEntity): Long

    @Update
    suspend fun update(subscriber: SubscriberEntity)

    @Query("DELETE FROM subscriber_tb WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM subscriber_tb")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber_tb")
    suspend fun getAll(): List<SubscriberEntity>
//    fun getAll(): LiveData<List<SubscriberEntity>>
}