package com.example.opiniaodetudo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.opiniaodetudo.infra.dao.ReviewDao
import com.example.opiniaodetudo.model.Review


@Database(entities = arrayOf(Review::class), version = 2, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase() {
    companion object {
        private var instance: ReviewDatabase? = null

        fun getInstance(context: Context): ReviewDatabase {
            if(instance == null) {
                instance = Room
                    .databaseBuilder(context, ReviewDatabase::class.java, "review_database")
                    .allowMainThreadQueries()
                    .build()
            }

            return instance!!
        }
    }

    abstract fun reviewDao(): ReviewDao
}