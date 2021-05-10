package com.android.espermobiles.repository

import android.util.Log
import com.android.espermobiles.api.MobileApi
import com.android.espermobiles.db.MobileDao
import com.android.espermobiles.db.converter.DataConverter
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class MobileRepositoryImpl(
    private val mobileService: MobileApi,
    private val dataConverter: DataConverter,
    private val mobileDao: MobileDao
) : MobileRepository {

   /* fetches data from api and adding it to DB
    before adding data to Db, it will remove the old data from DB if any*/
    override fun fetchDataFromAPI(): Completable {
        return mobileService.getAllDetails()
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

    //fetches feature data from DB
    override fun getFeaturesData(): Flowable<List<FeaturesData>> {
        return mobileDao.findAll()
    }

   /* fecthes exclusions data from DB
    returned list will have data if input matches with feature1 and option1
    OR if input matches with feature2 and option2*/
    override fun getExclusions(featureID: String, optionsID: String): List<ExclusionsData> {
        return mobileDao.getExclusion(featureID, optionsID)
    }
}