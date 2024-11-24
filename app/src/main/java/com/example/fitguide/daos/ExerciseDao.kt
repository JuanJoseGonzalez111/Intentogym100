package com.example.fitguide.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fitguide.Exercise
import com.example.fitguide.entities.ExerciseData

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercisedata WHERE type = :exerciseType")
    fun getByType(exerciseType: String): List<ExerciseData>

    @Insert
    fun insert(exerciseData: ExerciseData)

    @Update
    fun updateExercise(exerciseData: ExerciseData)

    @Delete
    fun deleteExercise(exerciseData: ExerciseData)
}
