package com.example.fitguide.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExerciseData (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "exerciseImage")
    val exerciseImage: String,

    @ColumnInfo(name = "type") // Nuevo campo para categorizar ejercicios
    val type: String
)