package com.example.fitguide.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exerciseCategories")
data class ExerciseCategoryData (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "category")
    val title: String,

    @ColumnInfo(name = "exerciseImage")
    val exerciseImage: String,
)