package com.android.espermobiles.repository

import com.android.espermobiles.db.model.FeaturesData
import com.android.espermobiles.util.AppResult
import io.reactivex.Completable
import io.reactivex.Flowable

interface MobileRepository {

    fun fetchDataFromAPI() : Completable

    fun getFeaturesData() : Flowable<List<FeaturesData>>
}