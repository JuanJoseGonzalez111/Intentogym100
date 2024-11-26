package com.example.fitguide.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fitguide.entities.ExerciseCategoryData

@Dao
interface ExerciseCategoryDao {

    @Query("SELECT * FROM exerciseCategories")
    fun getAll(): List<ExerciseCategoryData>

    @Query("SELECT * FROM exerciseCategories WHERE id = :id")
    fun find(id: Int): ExerciseCategoryData

    @Insert
    fun insert(exerciseData: ExerciseCategoryData)

    @Insert
    fun insertMany(vararg exerciseData: ExerciseCategoryData)

    @Update
    fun updateExercise(exerciseData: ExerciseCategoryData)

    @Delete
    fun deleteExercise(exerciseData: ExerciseCategoryData)
}