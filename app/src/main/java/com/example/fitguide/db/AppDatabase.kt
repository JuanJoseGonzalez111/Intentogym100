package com.example.fitguide.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fitguide.daos.ExerciseCategoryDao
import com.example.fitguide.daos.ExerciseListDao
import com.example.fitguide.entities.ExerciseCategoryData
import com.example.fitguide.entities.ExerciseListData

@Database(entities = [ExerciseCategoryData::class, ExerciseListData::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseCategoryDao(): ExerciseCategoryDao
    abstract fun exerciseListDao(): ExerciseListDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Crear la nueva tabla
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS exerciseList (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                category_id INTEGER NOT NULL,
                name TEXT NOT NULL,
                image_res_id TEXT NOT NULL
            )
        """)
    }
}

object DatabaseProvider {
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "exerciseData-db"
            )
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2) // Agregamos la migración
                .build()
        }
        return instance!!
    }
}
