package com.android.espermobiles.db.converter

import com.android.espermobiles.api.ResponseApiModel
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData

interface DataConverter {

    fun apiToFeatures(api: ResponseApiModel) : List<FeaturesData>

    fun apiToExclusions(api: ResponseApiModel) : List<ExclusionsData>
}