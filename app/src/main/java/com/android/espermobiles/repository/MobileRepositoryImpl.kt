package com.android.espermobiles.repository

import android.content.Context
import android.util.Log
import com.android.espermobiles.api.MobileApi
import com.android.espermobiles.db.MobileDao
import com.android.espermobiles.db.converter.DataConverter
import com.android.espermobiles.db.model.FeaturesData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class MobileRepositoryImpl(
        private val context: Context,
        private val mobileService: MobileApi,
        private val dataConverter: DataConverter,
        private val mobileDao: MobileDao
) : MobileRepository {

    override fun fetchDataFromAPI(): Completable {
        Log.d("TAG", "fetchDataFromAPI()")
        return mobileService.getAllDetails("https://my-json-server.typicode.com/mhrpatel12/esper-assignment/db")
                .subscribeOn(Schedulers.io())
                .firstOrError()
                .flatMapCompletable { response ->
                    if (response.isSuccessful) {
                        response.body()?.let { responseApi ->
                            val featuresDataList = dataConverter.apiToFeatures(responseApi)
                            val exclusionsDataList = dataConverter.apiToExclusions(responseApi)
                            mobileDao.deleteFeatures()
                            mobileDao.deleteExclusions()
                            mobileDao.addFeatures(featuresDataList)
                            mobileDao.addExclusions(exclusionsDataList)
                        }
                    }
                    Completable.complete()
                }
    }

    override fun getFeaturesData(): Flowable<List<FeaturesData>> {
        TODO("Not yet implemented")
    }

}