package com.queapps.cruddozero.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "subscriber_tb")
@Parcelize
data class SubscriberEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    @ColumnInfo(name = "name_subscriber") val name:String,
    @ColumnInfo(name = "email_subscriber") val email:String
): Parcelable
