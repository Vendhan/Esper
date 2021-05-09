package com.android.espermobiles.db

import androidx.room.*
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData
import io.reactivex.Flowable

@Dao
interface MobileDao {

    //query to get all the features to show in the UI
    @Query("SELECT * FROM Features")
    fun findAll(): Flowable<List<FeaturesData>>

    //adding featureModel after conversion from api response to table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun addFeatures(features: List<FeaturesData>)

    //adding exclusionsModel after conversion from api response to table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun addExclusions(exclusions: List<ExclusionsData>)

    //helps to delete all the rows from features table before adding new set of features in table
    @Query("DELETE FROM Features")
    fun deleteFeatures()

    //helps to delete all the rows from exclusions table before adding new set of exclusions in table
    @Query("DELETE FROM Exclusions")
    fun deleteExclusions()

    //query to find the exclusion by matching the user input
    @Query("SELECT * FROM Exclusions WHERE featureID1 == :featureID AND optionID1 == :optionsID")
    fun getExclusion1(featureID: String, optionsID: String): List<ExclusionsData>

    //query to find the exclusion by matching the user input
    @Query("SELECT * FROM Exclusions WHERE featureID2 == :featureID AND optionID12 == :optionsID")
    fun getExclusion2(featureID: String, optionsID: String): List<ExclusionsData>
}