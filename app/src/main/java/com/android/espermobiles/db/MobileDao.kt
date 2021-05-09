package com.android.espermobiles.db

import androidx.room.*
import com.android.espermobiles.db.model.ExclusionsData
import com.android.espermobiles.db.model.FeaturesData
import io.reactivex.Completable

@Dao
interface MobileDao {

    @Query("SELECT * FROM Features")
    fun findAll(): List<FeaturesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFeatures(users: List<FeaturesData>) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addExclusions(users: List<ExclusionsData>) : Completable

    @Query("DELETE FROM Features")
    fun deleteFeatures() : Completable

    @Query("DELETE FROM Exclusions")
    fun deleteExclusions() : Completable

    /*@Query("SELECT * FROM Exclusions")
    fun getExclusions(featureID: String, optionsID: String) : List<ExclusionsData>*/
}