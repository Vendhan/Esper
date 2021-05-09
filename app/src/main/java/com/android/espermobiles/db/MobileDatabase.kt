package com.android.espermobiles.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData

//RoomDB
@Database(
    entities = [FeaturesData::class, ExclusionsData::class],
    version = 1, exportSchema = false
)
abstract class MobileDatabase : RoomDatabase() {
    abstract val mobileDao: MobileDao
}