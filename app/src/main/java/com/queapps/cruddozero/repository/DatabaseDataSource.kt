package com.queapps.cruddozero.repository

import com.queapps.cruddozero.data.db.dao.SubscriberDao
import com.queapps.cruddozero.data.db.entity.SubscriberEntity
import kotlinx.coroutines.flow.Flow

class DatabaseDataSource(
    private val subscriberDao: SubscriberDao,
) : SubscriberRepository {
    override suspend fun insertSubscriber(name: String, email: String): Long {
        val subscriberEntity = SubscriberEntity(
            name = name,
            email = email,
        )

        return subscriberDao.insert(subscriberEntity)
    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {
        val subscriberEntity = SubscriberEntity(
            id = id,
            name = name,
            email = email,
        )
        subscriberDao.update(subscriberEntity)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDao.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDao.deleteAll()
    }

    override suspend fun getAllSubscribers(): Flow<List<SubscriberEntity>> =
        subscriberDao.getAll()
}
