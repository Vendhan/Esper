package com.android.espermobiles.repository

import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData
import io.reactivex.Completable
import io.reactivex.Flowable

interface MobileRepository {

    fun fetchDataFromAPI() : Completable

    fun getFeaturesData() : Flowable<List<FeaturesData>>

    fun getExclusions(featureID: String, optionsID: String) : List<ExclusionsData>
}