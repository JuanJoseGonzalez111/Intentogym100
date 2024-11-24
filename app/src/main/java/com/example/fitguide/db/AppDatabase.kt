package com.example.fitguide.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitguide.daos.ExerciseDao
import com.example.fitguide.entities.ExerciseData

@Database(entities = [ExerciseData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}

object DatabaseProvider{

    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase{

        if (instance == null){
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "exerciseData-db"
            ).build()
        }
        return instance!!
    }
}