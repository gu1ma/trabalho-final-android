package com.example.opiniaodetudo.infra.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import com.example.opiniaodetudo.model.Review
import java.util.*

class ReviewDAOSQLite{

    private val dbHelper: SQLiteOpenHelper

    constructor(context:Context){
    dbHelper = ReviewDBHelper(context)
    }

    fun save(name: String, review: String) {
        val writableDatabase = dbHelper.writableDatabase
        writableDatabase.insert(ReviewTableInfo.TABLE_NAME, null, ContentValues().apply {
            put(ReviewTableInfo.COLUMN_ID, UUID.randomUUID().toString())
            put(ReviewTableInfo.COLUMN_NAME, name)
            put(ReviewTableInfo.COLUMN_REVIEW, review)
        })
        writableDatabase.close()
    }


    /* Dando erro neste metodo, verificar dps */


//    fun listAll(): List<Review> {
//        val readableDatabase = dbHelper.readableDatabase
//        val cursor: Cursor = readableDatabase.selectAll(
//            ReviewTableInfo.TABLE_NAME,
//            arrayOf(
//                ReviewTableInfo.COLUMN_ID,
//                ReviewTableInfo.COLUMN_NAME,
//                ReviewTableInfo.COLUMN_REVIEW))
//        val list = mutableListOf<Review>()
//        while(cursor.moveToNext()){
//            list.add(createReview(cursor))
//        }
//        readableDatabase.close()
//        return list
//    }



}