package com.android.espermobiles.db.converter

import com.android.espermobiles.api.ResponseApiModel
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData
import java.util.*
import kotlin.collections.ArrayList

class DataConverterImpl : DataConverter {

    //conversion method to convert responseApi to featureModel
    override fun apiToFeatures(api: ResponseApiModel): List<FeaturesData> {
        val featuresDataList = ArrayList<FeaturesData>()
        api.features?.forEach { featureApi ->
            featureApi.options?.forEach { optionsApi ->
                val featureData = FeaturesData(
                    id = UUID.randomUUID().toString(),
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

    /*conversion method to convert responseApi to exclusionsModel
    it will convert list of exclusions from json to feature1, option1 and feature2, option2
    */
    override fun apiToExclusions(api: ResponseApiModel): List<ExclusionsData> {
        val exclusionsList = ArrayList<ExclusionsData>()
        api.exclusions?.forEach {
            val exclusionData = ExclusionsData(
                id = UUID.randomUUID().toString(),
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