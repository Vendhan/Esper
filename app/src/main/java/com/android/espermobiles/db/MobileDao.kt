package com.android.espermobiles.db

import androidx.room.*
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData
import io.reactivex.Flowable

@Dao
interface MobileDao {

    @Query("SELECT * FROM Features")
    fun findAll(): Flowable<List<FeaturesData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun addFeatures(features: List<FeaturesData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun addExclusions(exclusions: List<ExclusionsData>)

    @Query("DELETE FROM Features")
    fun deleteFeatures()

    @Query("DELETE FROM Exclusions")
    fun deleteExclusions()

    @Query("SELECT * FROM Exclusions WHERE featureID1 == :featureID AND optionID1 == :optionsID")
    fun getExclusion1(featureID: String, optionsID: String) : List<ExclusionsData>

    @Query("SELECT * FROM Exclusions WHERE featureID2 == :featureID AND optionID12 == :optionsID")
    fun getExclusion2(featureID: String, optionsID: String) : List<ExclusionsData>
}