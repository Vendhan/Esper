package com.android.espermobiles.core

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.android.espermobiles.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    //koin for dependency injection
    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                apiModule,
                viewModelModule,
                repositoryModule,
                networkModule,
                databaseModule
            )
        }
    }

    companion object {

        //to check the network connection status
        fun isOnline(ctx: Context): Boolean {
            try {
                val connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as? ConnectivityManager ?: return false
                val networkInfo = connectivityManager.activeNetworkInfo
                return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
            } catch (e: Exception) {
                Log.e("TAG", e.toString())
            }
            return false
        }
    }
}