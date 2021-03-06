package com.android.espermobiles.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Exclusion table to be stored in DB
@Entity(tableName = "Exclusions")
data class ExclusionsData(
        @PrimaryKey
        val id: String,
        @ColumnInfo(name = "featureID1")
        val featureID1: String,
        @ColumnInfo(name = "optionID1")
        val optionID1: String,
        @ColumnInfo(name = "featureID2")
        val featureID2: String,
        @ColumnInfo(name = "optionID12")
        val optionID2: String
)