package com.android.espermobiles.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Features table to be stored in DB
@Entity(tableName = "Features")
data class FeaturesData(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "featureID")
    val featureID : String,
    @ColumnInfo(name = "featureName")
    val featureName : String,
    @ColumnInfo(name = "optionID")
    val optionID : String,
    @ColumnInfo(name = "optionName")
    val optionName : String,
    @ColumnInfo(name = "optionIcon")
    val optionIcon : String,
)