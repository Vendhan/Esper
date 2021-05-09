package com.android.espermobiles.di

import android.content.Context
import com.android.espermobiles.api.MobileApi
import com.android.espermobiles.db.MobileDao
import com.android.espermobiles.db.converter.DataConverter
import com.android.espermobiles.db.converter.DataConverterImpl
import com.android.espermobiles.repository.MobileRepository
import com.android.espermobiles.repository.MobileRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

//di for repository impl files
val repositoryModule = module {

    fun provideMobileRepository(api: MobileApi, dataConverter: DataConverter, mobileDao: MobileDao, context: Context): MobileRepository {
        return MobileRepositoryImpl(api, dataConverter, mobileDao)
    }
    single { provideMobileRepository(get(), get(), get(), androidContext()) }

    fun provideDataConverter(): DataConverter {
        return DataConverterImpl()
    }
    single { provideDataConverter() }
}