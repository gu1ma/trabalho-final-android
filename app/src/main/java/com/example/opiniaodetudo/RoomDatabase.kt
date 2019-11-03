package com.example.opiniaodetudo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.opiniaodetudo.infra.dao.ReviewDao
import com.example.opiniaodetudo.model.Review


@Database(entities = arrayOf(Review::class), version = 2)
abstract class ReviewDatabase : RoomDatabase() {
    companion object {
        private var instance: RoomDatabase? = null
        fun getInstance(context: Context): RoomDatabase {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context, RoomDatabase::class.java, "review_database")
                    .build()
            }
            return instance!!
        }

    }

     abstract fun reviewDao(): ReviewDao
}