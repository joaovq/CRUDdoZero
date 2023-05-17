package com.queapps.cruddozero.repository

import com.queapps.cruddozero.data.db.entity.SubscriberEntity
import kotlinx.coroutines.flow.Flow

interface SubscriberRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscribers()

    suspend fun getAllSubscribers(): Flow<List<SubscriberEntity>>
//    fun getAllSubscribers(): LiveData<List<SubscriberEntity>>
}