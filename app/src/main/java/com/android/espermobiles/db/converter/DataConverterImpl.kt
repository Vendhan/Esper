package com.android.espermobiles.db.converter

import com.android.espermobiles.api.ResponseApiModel
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData

class DataConverterImpl : DataConverter {

    override fun apiToFeatures(api: ResponseApiModel): List<FeaturesData> {
        val featuresDataList = ArrayList<FeaturesData>()
        api.features?.forEach { featureApi ->
            featureApi.options?.forEach { optionsApi ->
                val featureData = FeaturesData(
                        id = 0,
                        featureID = featureApi.featureID ?: "",
                        featureName = featureApi.featureName ?: "",
                        optionID = optionsApi.optionsID ?: "",
                        optionIcon = optionsApi.optionsIcon ?: "",
                        optionName = optionsApi.optionsName ?: ""
                )
                featuresDataList.add(featureData)
            }
        }
        return featuresDataList
    }

    override fun apiToExclusions(api: ResponseApiModel): List<ExclusionsData> {
        val exclusionsList = ArrayList<ExclusionsData>()
        api.exclusions?.forEach {
            val exclusionData = ExclusionsData(
                    id = null,
                    featureID1 = it[0].featureID ?: "",
                    featureID2 = it[1].featureID ?: "",
                    optionID1 = it[0].optionID ?: "",
                    optionID2 = it[1].optionID ?: ""
            )
            exclusionsList.add(exclusionData)
        }
        return exclusionsList
    }
}