package com.android.espermobiles.di

import android.app.Application
import androidx.room.Room
import com.android.espermobiles.db.MobileDao
import com.android.espermobiles.db.MobileDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): MobileDatabase {
        return Room.databaseBuilder(application, MobileDatabase::class.java, "mobiles")
                .fallbackToDestructiveMigration()
                .build()
    }

    fun provideMobileDao(database: MobileDatabase): MobileDao {
        return  database.mobileDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideMobileDao(get()) }

}