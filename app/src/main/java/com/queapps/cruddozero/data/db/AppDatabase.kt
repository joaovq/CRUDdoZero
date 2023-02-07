package com.queapps.cruddozero.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.queapps.cruddozero.data.db.dao.SubscriberDao
import com.queapps.cruddozero.data.db.entity.SubscriberEntity

@Database(entities = [SubscriberEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase() {
     abstract val subscriberDao:SubscriberDao

     companion object{

          @Volatile
          private var INSTANCE:AppDatabase? = null

          fun getInstance(context: Context):AppDatabase{

               synchronized(this){
                    var instance = INSTANCE

                    if (instance == null){
                         instance = Room.databaseBuilder(
                              context,
                              AppDatabase::class.java,
                              "app_db"
                         ).build()

                         INSTANCE = instance
                    }

                    return instance
               }


          }


     }

}