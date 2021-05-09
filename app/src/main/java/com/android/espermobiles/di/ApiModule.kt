package com.android.espermobiles.di

import com.android.espermobiles.api.MobileApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideMobileApi(retrofit: Retrofit): MobileApi {
        return retrofit.create(MobileApi::class.java)
    }
    single { provideMobileApi(get()) }

}